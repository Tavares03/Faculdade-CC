package com.example.appfilme.uii

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appfilme.R
import com.example.appfilme.adapter.FilmeAdapter
import com.example.appfilme.database.DBHelper

class FavoritosActivity : AppCompatActivity() {
    var recycler: RecyclerView? = null
    var btnVoltar: ImageButton? = null
    var layoutVazio: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        recycler = findViewById(R.id.recyclerFavoritos)
        recycler!!.layoutManager = LinearLayoutManager(this)

        btnVoltar = findViewById(R.id.btnVoltar)
        layoutVazio = findViewById(R.id.layoutVazio)

        btnVoltar?.setOnClickListener {
            finish()
        }

        val db = DBHelper(this)
        val lista = db.listar()

        if (lista.isEmpty()) {
            recycler!!.visibility = View.GONE
            layoutVazio!!.visibility = View.VISIBLE
        } else {
            recycler!!.visibility = View.VISIBLE
            layoutVazio!!.visibility = View.GONE
            val adapter = FilmeAdapter(this, lista, true)
            recycler!!.adapter = adapter
        }
    }
}