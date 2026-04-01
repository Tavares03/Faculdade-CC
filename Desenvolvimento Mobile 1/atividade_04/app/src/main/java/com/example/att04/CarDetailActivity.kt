package com.example.att04

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.core.view.WindowCompat

class CarDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        val car = intent.getSerializableExtra("car") as? Car

        car?.let {
            findViewById<ImageView>(R.id.imgCarDetail).setImageResource(it.imageRes)
            findViewById<TextView>(R.id.tvDetailBrand).text = it.brand
            findViewById<TextView>(R.id.tvDetailModel).text = "${it.model} ${it.year}".trim()
            findViewById<TextView>(R.id.tvDetailPrice).text = it.price
            findViewById<TextView>(R.id.tvDetailSeller).text = it.sellerName
            findViewById<TextView>(R.id.tvDetailAddress).text = it.sellerAddress
            findViewById<TextView>(R.id.tvDetailPhone).text = it.sellerPhone

            findViewById<Button>(R.id.btnContact).setOnClickListener { _ ->
                Toast.makeText(this, "Entrando em contato com ${it.sellerName}...", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}