package com.example.rpgdesafio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StoryActivity : AppCompatActivity() {

    private lateinit var storyText: TextView
    private lateinit var choice1: Button
    private lateinit var choice2: Button
    private lateinit var image: ImageView

    private var step = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        storyText = findViewById(R.id.storyText)
        choice1 = findViewById(R.id.choice1)
        choice2 = findViewById(R.id.choice2)
        image = findViewById(R.id.storyImage)

        updateStory()

        choice1.setOnClickListener {
            if (step == 0) {
                step = 1
                updateStory()
            } else if (step == 1) {
                goToEnding("dominio")
            }
        }

        choice2.setOnClickListener {
            if (step == 0) {
                goToEnding("liberdade")
            } else if (step == 1) {
                goToEnding("liberdade")
            }
        }
    }

    private fun updateStory() {
        if (step == 0) {
            storyText.text = "Você encontra um aplicativo que prevê o futuro..."
            image.setImageResource(R.drawable.app_misterioso)
            choice1.text = "Usar o app"
            choice2.text = "Ignorar"
        } else if (step == 1) {
            storyText.text = "O app começa a acertar tudo. Você fica obcecado."
            image.setImageResource(R.drawable.decisao)
            choice1.text = "Continuar usando"
            choice2.text = "Desinstalar"
        }
    }

    private fun goToEnding(tipo: String) {
        val intent = Intent(this, EndingActivity::class.java)
        intent.putExtra("tipo", tipo)
        startActivity(intent)
    }
}