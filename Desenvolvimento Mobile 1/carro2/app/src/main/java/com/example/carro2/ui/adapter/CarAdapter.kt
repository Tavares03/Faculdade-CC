package com.example.carro2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.carro2.R
import com.example.carro2.data.entity.Car
import com.example.carro2.databinding.ItemCarBinding
import java.text.NumberFormat
import java.util.Locale

class CarAdapter(
    private val onClick: (Car) -> Unit,
    private val onDelete: (Car) -> Unit
) : ListAdapter<Car, CarAdapter.CarViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CarViewHolder(private val b: ItemCarBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun bind(car: Car) {
            b.tvBrand.text = car.brand
            b.tvModel.text = car.model

            val fmt = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            b.tvPrice.text = fmt.format(car.price)

            Glide.with(b.root.context)
                .load(car.imageUrl)
                .placeholder(R.drawable.ic_car_placeholder)
                .error(R.drawable.ic_car_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(b.ivCar)

            b.root.setOnClickListener { onClick(car) }
            b.btnDelete.setOnClickListener { onDelete(car) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Car>() {
        override fun areItemsTheSame(o: Car, n: Car) = o.id == n.id
        override fun areContentsTheSame(o: Car, n: Car) = o == n
    }
}
