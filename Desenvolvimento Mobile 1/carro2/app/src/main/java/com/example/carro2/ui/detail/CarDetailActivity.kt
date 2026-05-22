package com.example.carro2.ui.detail

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.carro2.R
import com.example.carro2.databinding.ActivityCarDetailBinding
import java.text.NumberFormat
import java.util.Locale

class CarDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarDetailBinding

    companion object {
        const val EXTRA_CAR_ID       = "car_id"
        const val EXTRA_CAR_BRAND    = "car_brand"
        const val EXTRA_CAR_MODEL    = "car_model"
        const val EXTRA_CAR_PRICE    = "car_price"
        const val EXTRA_CAR_IMAGE    = "car_image"
        const val EXTRA_CAR_DESC     = "car_desc"
        const val EXTRA_CAR_ENGINE   = "car_engine"
        const val EXTRA_CAR_POWER    = "car_power"
        const val EXTRA_CAR_TORQUE   = "car_torque"
        const val EXTRA_CAR_ACCEL    = "car_accel"
        const val EXTRA_CAR_TOPSPEED = "car_topspeed"
        const val EXTRA_CAR_TRANS    = "car_trans"
        const val EXTRA_CAR_ORIGIN   = "car_origin"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val brand    = intent.getStringExtra(EXTRA_CAR_BRAND)    ?: ""
        val model    = intent.getStringExtra(EXTRA_CAR_MODEL)    ?: ""
        val price    = intent.getDoubleExtra(EXTRA_CAR_PRICE, 0.0)
        val imageUrl = intent.getStringExtra(EXTRA_CAR_IMAGE)    ?: ""
        val desc     = intent.getStringExtra(EXTRA_CAR_DESC)     ?: ""
        val engine   = intent.getStringExtra(EXTRA_CAR_ENGINE)   ?: ""
        val power    = intent.getStringExtra(EXTRA_CAR_POWER)    ?: ""
        val torque   = intent.getStringExtra(EXTRA_CAR_TORQUE)   ?: ""
        val accel    = intent.getStringExtra(EXTRA_CAR_ACCEL)    ?: ""
        val topSpeed = intent.getStringExtra(EXTRA_CAR_TOPSPEED) ?: ""
        val trans    = intent.getStringExtra(EXTRA_CAR_TRANS)    ?: ""
        val origin   = intent.getStringExtra(EXTRA_CAR_ORIGIN)   ?: ""

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.collapsingToolbar.title = "$brand $model"

        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.ic_car_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(binding.ivCarDetail)

        binding.tvDetailBrand.text = brand
        binding.tvDetailModel.text = model

        val fmt = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        binding.tvDetailPrice.text = fmt.format(price)
        binding.tvDetailDescription.text = desc

        // Fill spec rows via included layout views
        setSpecRow(R.id.rowEngine,   "Motor",         engine)
        setSpecRow(R.id.rowPower,    "Potência",      power)
        setSpecRow(R.id.rowTorque,   "Torque",        torque)
        setSpecRow(R.id.rowAccel,    "0-100 km/h",    accel)
        setSpecRow(R.id.rowTopSpeed, "Vel. Máxima",   topSpeed)
        setSpecRow(R.id.rowTrans,    "Câmbio",        trans)
        setSpecRow(R.id.rowOrigin,   "Origem",        origin)
    }

    private fun setSpecRow(rowId: Int, label: String, value: String) {
        val row = binding.root.findViewById<android.view.View>(rowId)
        row.findViewById<TextView>(R.id.tvSpecLabel).text = label
        row.findViewById<TextView>(R.id.tvSpecValue).text = value
    }

    override fun onSupportNavigateUp(): Boolean { finish(); return true }
}
