package com.example.att04

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarAdapter(
    private val cars: List<Car>,
    private val onBuyClick: (Car) -> Unit
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCar: ImageView = itemView.findViewById(R.id.imgCar)
        val tvBrand: TextView = itemView.findViewById(R.id.tvBrand)
        val tvModel: TextView = itemView.findViewById(R.id.tvModel)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val btnBuy: Button = itemView.findViewById(R.id.btnBuy)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = cars[position]
        holder.imgCar.setImageResource(car.imageRes)
        holder.tvBrand.text = car.brand
        holder.tvModel.text = "${car.model} ${car.year}"
        holder.tvPrice.text = car.price
        holder.btnBuy.setOnClickListener { onBuyClick(car) }
    }

    override fun getItemCount(): Int = cars.size
}
