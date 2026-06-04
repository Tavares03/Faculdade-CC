package com.example.appfilme.uii

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appfilme.R
import com.example.appfilme.api.ApiClient
import com.example.appfilme.api.ApiService
import com.example.appfilme.database.DBHelper
import com.example.appfilme.model.CreditosResponse
import com.example.appfilme.model.Filme
import com.example.appfilme.model.GeneroResponse
import com.example.appfilme.model.ReleaseDatesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalhesActivity : AppCompatActivity() {

    private val API_KEY = "e4c56c618abd09b7336341af68e594fd"
    private val service by lazy { ApiClient.retrofit.create(ApiService::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        val txtTitulo = findViewById<TextView>(R.id.txtTitulo)
        val txtSinopse = findViewById<TextView>(R.id.txtSinopse)
        val txtNota = findViewById<TextView>(R.id.txtNota)
        val txtGeneros = findViewById<TextView>(R.id.txtGeneros)
        val txtClassificacao = findViewById<TextView>(R.id.txtClassificacao)
        val txtElenco = findViewById<TextView>(R.id.txtElenco)
        val btnFavoritar = findViewById<Button>(R.id.btnFavoritar)
        val btnVoltar = findViewById<ImageButton>(R.id.btnVoltar)

        val filmeId = intent.getIntExtra("id", 0)
        val titulo = intent.getStringExtra("titulo")
        val sinopse = intent.getStringExtra("sinopse")
        val nota = intent.getDoubleExtra("nota", 0.0)
        val poster = intent.getStringExtra("poster")

        txtTitulo.text = titulo
        txtSinopse.text = sinopse
        txtNota.text = "  ${String.format("%.1f", nota)} / 10"

        btnVoltar.setOnClickListener { finish() }

        btnFavoritar.setOnClickListener {
            val db = DBHelper(this)
            val filme = Filme()
            filme.title = titulo
            filme.overview = sinopse
            filme.vote_average = nota
            filme.poster_path = poster
            db.inserir(filme)
            Toast.makeText(this, "✅ Adicionado aos favoritos!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, FavoritosActivity::class.java))
        }

        if (filmeId != 0) {
            buscarGeneros(filmeId, txtGeneros)
            buscarElenco(filmeId, txtElenco)
            buscarClassificacao(filmeId, txtClassificacao)
        }
    }

    private fun buscarGeneros(filmeId: Int, txtGeneros: TextView) {
        service.getGeneros(API_KEY).enqueue(object : Callback<GeneroResponse> {
            override fun onResponse(call: Call<GeneroResponse>, response: Response<GeneroResponse>) {
                val generos = response.body()?.genres ?: return
                val ids = intent.getIntArrayExtra("genre_ids")
                if (ids != null && ids.isNotEmpty()) {
                    val mapa = generos.associateBy { it.id }
                    // Corrigido: convertendo para Iterable para usar mapNotNull
                    val nomes = ids.asIterable().mapNotNull { mapa[it]?.name }.take(3).joinToString(" • ")
                    if (nomes.isNotEmpty()) {
                        txtGeneros.text = nomes
                        txtGeneros.visibility = View.VISIBLE
                    }
                }
            }
            override fun onFailure(call: Call<GeneroResponse>, t: Throwable) {}
        })
    }

    private fun buscarElenco(filmeId: Int, txtElenco: TextView) {
        service.getCreditos(filmeId, API_KEY).enqueue(object : Callback<CreditosResponse> {
            override fun onResponse(call: Call<CreditosResponse>, response: Response<CreditosResponse>) {
                val elenco = response.body()?.cast ?: return
                if (elenco.isEmpty()) {
                    txtElenco.text = "Não disponível"
                    return
                }
                val texto = elenco.take(6).joinToString("\n") { "• ${it.name}  —  ${it.character}" }
                txtElenco.text = texto
            }
            override fun onFailure(call: Call<CreditosResponse>, t: Throwable) {
                txtElenco.text = "Não disponível"
            }
        })
    }

    private fun buscarClassificacao(filmeId: Int, txtClassificacao: TextView) {
        service.getClassificacao(filmeId, API_KEY).enqueue(object : Callback<ReleaseDatesResponse> {
            override fun onResponse(call: Call<ReleaseDatesResponse>, response: Response<ReleaseDatesResponse>) {
                val paises = response.body()?.results ?: return

                val entry = paises.firstOrNull { it.iso_3166_1 == "BR" }
                    ?: paises.firstOrNull { it.iso_3166_1 == "US" }

                val cert = entry?.release_dates
                    ?.firstOrNull { it.certification.isNotEmpty() }
                    ?.certification

                if (!cert.isNullOrEmpty()) {
                    txtClassificacao.text = cert
                    txtClassificacao.visibility = View.VISIBLE
                }
            }
            override fun onFailure(call: Call<ReleaseDatesResponse>, t: Throwable) {}
        })
    }
}