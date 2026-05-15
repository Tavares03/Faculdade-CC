package com.seupacote.conversor

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.seupacote.conversor.databinding.ActivityMainBinding
import com.seupacote.conversor.viewmodel.ConversorViewModel
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ConversorViewModel by viewModels()

    data class Moeda(val codigo: String, val nome: String, val bandeira: String)

    private val moedas = listOf(
        Moeda("BRL", "Real Brasileiro",   "🇧🇷"),
        Moeda("USD", "Dólar Americano",   "🇺🇸"),
        Moeda("EUR", "Euro",              "🇪🇺"),
        Moeda("GBP", "Libra Esterlina",   "🇬🇧"),
        Moeda("JPY", "Iene Japonês",      "🇯🇵"),
        Moeda("ARS", "Peso Argentino",    "🇦🇷"),
        Moeda("CAD", "Dólar Canadense",   "🇨🇦"),
        Moeda("AUD", "Dólar Australiano", "🇦🇺"),
        Moeda("CHF", "Franco Suíço",      "🇨🇭"),
        Moeda("CNY", "Yuan Chinês",       "🇨🇳")
    )

    private var posicaoOrigem = 0   // BRL
    private var posicaoDestino = 1  // USD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Barra de status transparente
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#0D0D1A")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.etValor.setHintTextColor(Color.parseColor("#333355"))

        configurarSpinners()
        configurarObservers()
        configurarBotoes()

        // Busca inicial
        viewModel.buscarTaxas(moedas[posicaoOrigem].codigo)
    }

    private fun configurarSpinners() {
        val labels = moedas.map { "${it.bandeira}  ${it.codigo} — ${it.nome}" }

        val adapter = object : ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            labels
        ) {
            override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
                val view = super.getView(position, convertView, parent)
                (view as? TextView)?.apply {
                    setTextColor(Color.WHITE)
                    textSize = 15f
                }
                return view
            }

            override fun getDropDownView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
                val view = super.getDropDownView(position, convertView, parent)
                (view as? TextView)?.apply {
                    setTextColor(Color.WHITE)
                    setBackgroundColor(Color.parseColor("#1A1A2E"))
                    textSize = 14f
                    setPadding(32, 24, 32, 24)
                }
                return view
            }
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerOrigem.adapter = adapter
        binding.spinnerDestino.adapter = adapter

        binding.spinnerOrigem.setSelection(posicaoOrigem)
        binding.spinnerDestino.setSelection(posicaoDestino)

        binding.spinnerOrigem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position == posicaoOrigem) return
                posicaoOrigem = position
                binding.tvSiglaMoedaOrigem.text = moedas[position].codigo
                viewModel.buscarTaxas(moedas[position].codigo)
                viewModel.limparResultado()
                binding.tvResultado.text = "0,00"
                binding.tvTaxa.text = "Carregando taxas..."
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        binding.spinnerDestino.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                posicaoDestino = position
                binding.tvSiglaMoedaDestino.text = moedas[position].codigo
                viewModel.limparResultado()
                binding.tvResultado.text = "0,00"
                binding.tvTaxa.text = "Selecione as moedas e pressione Converter"
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Siglas iniciais
        binding.tvSiglaMoedaOrigem.text = moedas[posicaoOrigem].codigo
        binding.tvSiglaMoedaDestino.text = moedas[posicaoDestino].codigo
    }

    private fun configurarObservers() {
        viewModel.loading.observe(this) { carregando ->
            binding.progressBar.visibility = if (carregando) View.VISIBLE else View.GONE
            binding.btnConverter.isEnabled = !carregando
            if (carregando) {
                binding.tvTaxa.text = "Carregando taxas..."
                binding.tvErro.visibility = View.GONE
            }
        }

        viewModel.erro.observe(this) { mensagem ->
            if (!mensagem.isNullOrEmpty()) {
                binding.tvErro.text = mensagem
                binding.tvErro.visibility = View.VISIBLE
            } else {
                binding.tvErro.visibility = View.GONE
            }
        }

        viewModel.resultado.observe(this) { valor ->
            if (valor != null) {
                val fmt = NumberFormat.getNumberInstance(Locale("pt", "BR"))
                fmt.maximumFractionDigits = 4
                fmt.minimumFractionDigits = 2
                binding.tvResultado.text = fmt.format(valor)
            }
        }

        viewModel.taxaAtual.observe(this) { triple ->
            if (triple != null) {
                val (origem, destino, taxa) = triple
                val fmt = NumberFormat.getNumberInstance(Locale("pt", "BR"))
                fmt.maximumFractionDigits = 4
                fmt.minimumFractionDigits = 4
                binding.tvTaxa.text = "1 $origem = ${fmt.format(taxa)} $destino"
            }
        }

        viewModel.taxas.observe(this) { taxas ->
            if (taxas != null && taxas.isNotEmpty()) {
                binding.tvTaxa.text = "Taxas carregadas ✓  Pronto para converter"
            }
        }
    }

    private fun configurarBotoes() {
        binding.btnConverter.setOnClickListener {
            val textoValor = binding.etValor.text.toString()
                .replace(",", ".")
                .trim()

            if (textoValor.isEmpty()) {
                binding.tvErro.text = "Digite um valor para converter"
                binding.tvErro.visibility = View.VISIBLE
                return@setOnClickListener
            }

            val valor = textoValor.toDoubleOrNull()
            if (valor == null || valor <= 0.0) {
                binding.tvErro.text = "Digite um valor válido maior que zero"
                binding.tvErro.visibility = View.VISIBLE
                return@setOnClickListener
            }

            if (posicaoOrigem == posicaoDestino) {
                binding.tvErro.text = "Selecione moedas diferentes"
                binding.tvErro.visibility = View.VISIBLE
                return@setOnClickListener
            }

            binding.tvErro.visibility = View.GONE

            viewModel.converter(
                valor,
                moedas[posicaoOrigem].codigo,
                moedas[posicaoDestino].codigo
            )
        }

        // Botão de trocar moedas
        binding.btnTrocar.setOnClickListener {
            val tmpPos = posicaoOrigem
            posicaoOrigem = posicaoDestino
            posicaoDestino = tmpPos

            // Atualiza spinners sem disparar listeners desnecessariamente
            binding.spinnerOrigem.setSelection(posicaoOrigem)
            binding.spinnerDestino.setSelection(posicaoDestino)
            binding.tvSiglaMoedaOrigem.text = moedas[posicaoOrigem].codigo
            binding.tvSiglaMoedaDestino.text = moedas[posicaoDestino].codigo

            // Busca taxas para a nova base
            viewModel.buscarTaxas(moedas[posicaoOrigem].codigo)
            viewModel.limparResultado()
            binding.tvResultado.text = "0,00"
            binding.etValor.text?.clear()
        }
    }
}
