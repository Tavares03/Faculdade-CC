package com.example.rpgdesafio

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class EndingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ending)

        val image = findViewById<ImageView>(R.id.endImage)
        val text = findViewById<TextView>(R.id.endText)

        val tipo = intent.getStringExtra("tipo")

        if (tipo == "dominio") {
            image.setImageResource(R.drawable.final_dominar)
            text.text = "Você dominou o futuro... mas perdeu sua humanidade."
        } else {
            image.setImageResource(R.drawable.final_liberdade)
            text.text = "Você destruiu o app e recuperou sua liberdade."
        }
        val btnRestart = findViewById<Button>(R.id.btnRestart)

        btnRestart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // fecha a tela atual
        }

    }
}