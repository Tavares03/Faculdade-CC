package com.example.appfilme

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appfilme.api.ApiClient
import com.example.appfilme.api.ApiService
import com.example.appfilme.adapter.FilmeAdapter
import com.example.appfilme.model.Filme
import com.example.appfilme.model.GeneroResponse
import com.example.appfilme.model.MovieResponse
import com.example.appfilme.uii.FavoritosActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: FilmeAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var editBusca: EditText
    private lateinit var btnBuscar: ImageButton
    private lateinit var btnMenu: ImageButton
    private lateinit var txtSubtitulo: TextView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navPaginaPrincipal: LinearLayout
    private lateinit var navFavoritos: LinearLayout

    private val service by lazy { ApiClient.retrofit.create(ApiService::class.java) }
    private val API_KEY = "e4c56c618abd09b7336341af68e594fd"

    private val listaFilmes = mutableListOf<Filme>()
    private var paginaAtual = 1
    private var totalPaginas = 1
    private var carregando = false
    private var termoBusca = ""

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
        progressBar = findViewById(R.id.progressBar)
        editBusca = findViewById(R.id.editBusca)
        btnBuscar = findViewById(R.id.btnBuscar)
        btnMenu = findViewById(R.id.btnMenu)
        txtSubtitulo = findViewById(R.id.txtSubtitulo)
        drawerLayout = findViewById(R.id.drawerLayout)
        navPaginaPrincipal = findViewById(R.id.navPaginaPrincipal)
        navFavoritos = findViewById(R.id.navFavoritos)

        adapter = FilmeAdapter(this, listaFilmes)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        // Fechar drawer com botão Voltar (API moderna, sem onBackPressed depreciado)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItems = layoutManager.itemCount
                val ultimoVisivel = layoutManager.findLastVisibleItemPosition()
                if (!carregando && paginaAtual < totalPaginas && ultimoVisivel >= totalItems - 3) {
                    paginaAtual++
                    carregarFilmes()
                }
            }
        })

        // Lupa para buscar
        btnBuscar.setOnClickListener { executarBusca() }

        editBusca.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                executarBusca()
                true
            } else false
        }

        // 3 pontinhos abre o drawer
        btnMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Página Principal: apenas fecha o drawer
        navPaginaPrincipal.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        // Favoritos: abre a FavoritosActivity
        navFavoritos.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            startActivity(Intent(this, FavoritosActivity::class.java))
        }

        carregarGeneros()
    }

    private fun carregarGeneros() {
        service.getGeneros(API_KEY).enqueue(object : Callback<GeneroResponse> {
            override fun onResponse(call: Call<GeneroResponse>, response: Response<GeneroResponse>) {
                val generos = response.body()?.genres ?: emptyList()
                val mapa = generos.associate { it.id to it.name }
                adapter.setMapaGeneros(mapa)
                carregarFilmes()
            }
            override fun onFailure(call: Call<GeneroResponse>, t: Throwable) {
                carregarFilmes()
            }
        })
    }

    private fun executarBusca() {
        val termo = editBusca.text.toString().trim()
        termoBusca = termo
        paginaAtual = 1
        totalPaginas = 1
        listaFilmes.clear()
        adapter.notifyDataSetChanged()

        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editBusca.windowToken, 0)

        txtSubtitulo.text = if (termo.isEmpty()) "Em alta agora" else "Resultados para \"$termo\""

        carregarFilmes()
    }

    private fun carregarFilmes() {
        if (carregando) return
        carregando = true
        progressBar.visibility = View.VISIBLE

        val call: Call<MovieResponse> = if (termoBusca.isEmpty()) {
            service.getFilmesEmAlta(API_KEY, "pt-BR", paginaAtual)
        } else {
            service.buscarFilmes(API_KEY, termoBusca, "pt-BR", paginaAtual)
        }

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                carregando = false
                progressBar.visibility = View.GONE
                val body = response.body() ?: return
                totalPaginas = body.total_pages ?: 1
                val inicio = listaFilmes.size
                listaFilmes.addAll(body.results ?: emptyList())
                adapter.notifyItemRangeInserted(inicio, body.results?.size ?: 0)
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                carregando = false
                progressBar.visibility = View.GONE
                t.printStackTrace()
            }
        })
    }
}
