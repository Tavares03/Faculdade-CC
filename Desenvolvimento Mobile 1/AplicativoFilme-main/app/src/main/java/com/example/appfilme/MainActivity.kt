package com.example.appfilme

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appfilme.api.ApiClient
import com.example.appfilme.api.ApiService
import com.example.appfilme.adapter.FilmeAdapter
import com.example.appfilme.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: FilmeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val viewPrincipal = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(viewPrincipal) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        recycler = findViewById(R.id.recyclerFilmes)
        recycler.layoutManager = LinearLayoutManager(this)

        val service = ApiClient.retrofit.create(ApiService::class.java)

        service.getFilmesEmAlta("e4c56c618abd09b7336341af68e594fd",  "pt-BR")
            .enqueue(object : Callback<MovieResponse> {

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    val lista = response.body()?.results ?: emptyList()

                    adapter = FilmeAdapter(this@MainActivity, lista)
                    recycler.adapter = adapter
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                }

            })
    }
}