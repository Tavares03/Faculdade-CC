package com.example.atividade01

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botao = findViewById<Button>(R.id.btnSortear)
        val imagemDado = findViewById<ImageView>(R.id.imgDado)

        botao.setOnClickListener {

            val numero = Random.nextInt(1,7)

            val imagem = when(numero){

                1 -> R.drawable.dice1
                2 -> R.drawable.dice2
                3 -> R.drawable.dice3
                4 -> R.drawable.dice4
                5 -> R.drawable.dice5
                else -> R.drawable.dice6
            }

            imagemDado.setImageResource(imagem)

            Toast.makeText(this,"Número sorteado: $numero",Toast.LENGTH_SHORT).show()

        }
    }
}