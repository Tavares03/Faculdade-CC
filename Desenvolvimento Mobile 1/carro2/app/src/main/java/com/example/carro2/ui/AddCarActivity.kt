package com.example.carro2.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.carro2.data.entity.Car
import com.example.carro2.databinding.ActivityAddCarBinding
import com.example.carro2.ui.viewmodel.CarViewModel

class AddCarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCarBinding
    private val viewModel: CarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Cadastrar Carro"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSave.setOnClickListener { saveCar() }
    }

    private fun saveCar() {
        val brand = binding.etBrand.text.toString().trim()
        val model = binding.etModel.text.toString().trim()
        val priceStr = binding.etPrice.text.toString().trim()
        val imageUrl = binding.etImageUrl.text.toString().trim()
        val desc = binding.etDescription.text.toString().trim()

        if (brand.isEmpty()) { binding.etBrand.error = "Informe a marca"; return }
        if (model.isEmpty()) { binding.etModel.error = "Informe o modelo"; return }
        if (priceStr.isEmpty()) { binding.etPrice.error = "Informe o preço"; return }

        val price = priceStr.toDoubleOrNull()
        if (price == null) { binding.etPrice.error = "Preço inválido"; return }

        viewModel.insert(
            Car(brand = brand, model = model, price = price,
                imageUrl = imageUrl, description = desc)
        )
        Toast.makeText(this, "Carro cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean { finish(); return true }
}
