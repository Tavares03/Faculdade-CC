package com.example.att04

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE


        val cars = listOf(
            Car(
                id = 1,
                brand = "Ferrari",
                model = "purosangue",
                year = "2024",
                price = "R$ 8.5 Milhões",
                imageRes = R.drawable.ferrari,
                sellerName = "Gabriel Tavares",
                sellerAddress = "Av. Paulista, 1000 - São Paulo, SP",
                sellerPhone = "(11) 99999-0001"
            ),
            Car(
                id = 2,
                brand = "Lamborghini",
                model = "Lamborghini Veneno",
                year = "2013",
                price = "R$ 50 Milhões",
                imageRes = R.drawable.lamborguini_veneno,
                sellerName = "Henrique Adson",
                sellerAddress = "Rua Augusta, 500 - São Paulo, SP",
                sellerPhone = "(11) 99999-0002"
            ),
            Car(
                id = 3,
                brand = "Bugatti",
                model = "Bugatti Chiron Super Sport 2025",
                year = "2025",
                price = "R$ 70 Milhões",
                imageRes = R.drawable.buggati,
                sellerName = "Cadu Goat",
                sellerAddress = "Av. Brasil, 2500 - Rio de Janeiro, RJ",
                sellerPhone = "(21) 99999-0003"
            ),
            Car(
                id = 4,
                brand = "Porsche",
                model = "Porsche 911 GT3 RS",
                year = "2024",
                price = "R$ 7 Milhões",
                imageRes = R.drawable.porche,
                sellerName = "J.A",
                sellerAddress = "Rua das Flores, 100 - Curitiba, PR",
                sellerPhone = "(41) 99999-0004"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CarAdapter(cars) { car ->
            val intent = Intent(this, CarDetailActivity::class.java)
            intent.putExtra("car", car)
            startActivity(intent)
        }
    }
}