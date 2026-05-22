package com.example.carro2.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.carro2.data.entity.Car

@Dao
interface CarDao {
    @Query("SELECT * FROM cars ORDER BY brand ASC")
    fun getAllCars(): LiveData<List<Car>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: Car)

    @Delete
    suspend fun deleteCar(car: Car)
}
