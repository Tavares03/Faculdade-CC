package com.example.atividade03

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referenciando os componentes do XML
        val etAmount = findViewById<TextInputEditText>(R.id.etAmount)
        val rgFriendship = findViewById<RadioGroup>(R.id.rgFriendship)
        val switchRound = findViewById<SwitchMaterial>(R.id.switchRound)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        // Ação do botão calcular
        btnCalculate.setOnClickListener {
            val amountText = etAmount.text.toString()

            // Validação simples
            if (amountText.isEmpty()) {
                etAmount.error = "Insira um valor"
                return@setOnClickListener
            }

            val amount = amountText.toDoubleOrNull() ?: 0.0

            // Definindo a taxa de juros com base no RadioButton selecionado
            val interestRate = when (rgFriendship.checkedRadioButtonId) {
                R.id.rbBestFriend -> 0.00
                R.id.rbFriend -> 0.05
                R.id.rbColleague -> 0.10
                R.id.rbStranger -> 0.25
                else -> 0.00
            }

            // Calculando o total
            var totalToPay = amount + (amount * interestRate)

            // Arredondando para cima se a chave estiver ativada
            if (switchRound.isChecked) {
                totalToPay = ceil(totalToPay)
            }

            // Formatando para Moeda Brasileira (R$)
            val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            tvResult.text = format.format(totalToPay)
        }
    }
}