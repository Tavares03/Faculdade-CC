package com.example.carro2.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carro2.data.entity.Car
import com.example.carro2.databinding.ActivityMainBinding
import com.example.carro2.ui.adapter.CarAdapter
import com.example.carro2.ui.detail.CarDetailActivity
import com.example.carro2.ui.viewmodel.CarViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CarViewModel by viewModels()
    private lateinit var adapter: CarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        setupRecyclerView()
        observeViewModel()

        binding.fabAddCar.setOnClickListener {
            startActivity(Intent(this, AddCarActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        adapter = CarAdapter(
            onClick = { car ->
                val intent = Intent(this, CarDetailActivity::class.java)
                intent.putExtra(CarDetailActivity.EXTRA_CAR_ID, car.id)
                intent.putExtra(CarDetailActivity.EXTRA_CAR_BRAND, car.brand)
                intent.putExtra(CarDetailActivity.EXTRA_CAR_MODEL, car.model)
                intent.putExtra(CarDetailActivity.EXTRA_CAR_PRICE, car.price)
                intent.putExtra(CarDetailActivity.EXTRA_CAR_IMAGE, car.imageUrl)
                intent.putExtra(CarDetailActivity.EXTRA_CAR_DESC, car.description)
                intent.putExtra(CarDetailActivity.EXTRA_CAR_ENGINE, car.engine)
                intent.putExtra(CarDetailActivity.EXTRA_CAR_POWER, car.power)
                intent.putExtra(CarDetailActivity.EXTRA_CAR_TORQUE, car.torque)
                intent.putExtra(CarDetailActivity.EXTRA_CAR_ACCEL, car.acceleration)
                intent.putExtra(CarDetailActivity.EXTRA_CAR_TOPSPEED, car.topSpeed)
                intent.putExtra(CarDetailActivity.EXTRA_CAR_TRANS, car.transmission)
                intent.putExtra(CarDetailActivity.EXTRA_CAR_ORIGIN, car.origin)
                startActivity(intent)
            },
            onDelete = { car -> confirmDelete(car) }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.allCars.observe(this) { cars ->
            adapter.submitList(cars)
        }
    }

    private fun confirmDelete(car: Car) {
        AlertDialog.Builder(this)
            .setTitle("Remover carro")
            .setMessage("Deseja remover o ${car.brand} ${car.model}?")
            .setPositiveButton("Remover") { _, _ -> viewModel.delete(car) }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}
