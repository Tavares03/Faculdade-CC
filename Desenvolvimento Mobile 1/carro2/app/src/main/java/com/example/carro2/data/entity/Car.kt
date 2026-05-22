package com.example.carro2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val brand: String,
    val model: String,
    val price: Double,
    val imageUrl: String,
    val description: String = "",
    val engine: String = "",
    val power: String = "",
    val torque: String = "",
    val acceleration: String = "",
    val topSpeed: String = "",
    val transmission: String = "",
    val origin: String = ""
)
