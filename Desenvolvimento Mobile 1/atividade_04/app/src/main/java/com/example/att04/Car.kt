package com.example.att04

import java.io.Serializable

data class Car(
    val id: Int,
    val brand: String,
    val model: String,
    val year: String,
    val price: String,
    val imageRes: Int,
    val sellerName: String,
    val sellerAddress: String,
    val sellerPhone: String
) : Serializable
