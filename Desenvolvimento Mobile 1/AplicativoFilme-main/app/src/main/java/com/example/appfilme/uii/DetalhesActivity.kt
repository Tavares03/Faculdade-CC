package com.example.appfilme.uii

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.appfilme.R
import com.example.appfilme.database.DBHelper
import com.example.appfilme.model.Filme

class DetalhesActivity : AppCompatActivity() {

    var txtTitulo: TextView? = null
    var txtSinopse: TextView? = null
    var txtNota: TextView? = null
    var btnFavoritar: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        txtTitulo = findViewById(R.id.txtTitulo)
        txtSinopse = findViewById(R.id.txtSinopse)
        txtNota = findViewById(R.id.txtNota)
        btnFavoritar = findViewById(R.id.btnFavoritar)


        val titulo = intent.getStringExtra("titulo")
        val sinopse = intent.getStringExtra("sinopse")
        val nota = intent.getDoubleExtra("nota", 0.0)
        val poster = intent.getStringExtra("poster")


        txtTitulo?.text = titulo
        txtSinopse?.text = sinopse
        val notaFormatada = String.format("%.2f", nota)
        txtNota?.text = "Nota: $notaFormatada"


        btnFavoritar?.setOnClickListener {

            val db = DBHelper(this)

            val filme = Filme()
            filme.title = titulo
            filme.overview = sinopse
            filme.vote_average = nota
            filme.poster_path = poster


            db.inserir(filme)


            val intent = Intent(this, FavoritosActivity::class.java)
            startActivity(intent)
        }
    }
}