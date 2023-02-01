package com.example.indigofera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.indigofera.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val article = intent.getParcelableExtra<Article>("DATA") as Article
        binding.tvTitle.text = article.title
        val content = article.text?.replace("\\n", System.getProperty("line.separator"))
        binding.tvContent.text = content
        binding.tvAuthor.text = article.author
        Glide.with(this)
            .load(article.photo_url) // URL Gambar
            .fitCenter()
            .into(binding.ivArticle) // imageView mana yang akan diterapkan
    }
}