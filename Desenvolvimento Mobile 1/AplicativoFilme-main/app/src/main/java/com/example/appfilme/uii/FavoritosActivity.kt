package com.example.appfilme.uii


import  android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appfilme.R
import com.example.appfilme.adapter.FilmeAdapter
import com.example.appfilme.database.DBHelper
import com.example.appfilme.model.Filme

class FavoritosActivity : AppCompatActivity() {
    var recycler: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)


        recycler = findViewById<RecyclerView?>(R.id.recyclerFavoritos)
        recycler!!.setLayoutManager(LinearLayoutManager(this))


        val db: DBHelper = DBHelper(this)


        val lista: MutableList<Filme?>? = db.listar()


        val adapter = FilmeAdapter(this, lista)
        recycler!!.setAdapter(adapter)
    }
}