package com.example.carro2.repository

import androidx.lifecycle.LiveData
import com.example.carro2.data.dao.CarDao
import com.example.carro2.data.entity.Car

class CarRepository(private val carDao: CarDao) {
    val allCars: LiveData<List<Car>> = carDao.getAllCars()

    suspend fun insert(car: Car) = carDao.insertCar(car)
    suspend fun delete(car: Car) = carDao.deleteCar(car)
}
