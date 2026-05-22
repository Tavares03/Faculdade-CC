package com.example.carro2.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.carro2.data.database.CarDatabase
import com.example.carro2.data.entity.Car
import com.example.carro2.repository.CarRepository
import kotlinx.coroutines.launch

class CarViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CarRepository
    val allCars: LiveData<List<Car>>

    init {
        val carDao = CarDatabase.getDatabase(application).carDao()
        repository = CarRepository(carDao)
        allCars = repository.allCars
    }

    fun insert(car: Car) = viewModelScope.launch { repository.insert(car) }
    fun delete(car: Car) = viewModelScope.launch { repository.delete(car) }
}
