package com.example.indigofera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.indigofera.databinding.ActivityDetailBinding
import com.example.indigofera.databinding.ActivityPotensiBinding

class PotensiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPotensiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPotensiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this)
            .load(R.drawable.potensi1)
            .fitCenter()
            .into(binding.ivPosterPotensi1)
        Glide.with(this)
            .load(R.drawable.potensi2)
            .fitCenter()
            .into(binding.ivPosterPotensi2)
        Glide.with(this)
            .load(R.drawable.potensi3)
            .fitCenter()
            .into(binding.ivPosterPotensi3)
        Glide.with(this)
            .load(R.drawable.potensi4)
            .fitCenter()
            .into(binding.ivPosterPotensi4)
        Glide.with(this)
            .load(R.drawable.potensi5)
            .fitCenter()
            .into(binding.ivPosterPotensi5)
    }
}