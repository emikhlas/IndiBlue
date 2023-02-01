package com.example.indigofera

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.indigofera.databinding.ActivityPosterBinding

class PosterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPosterBinding
    private lateinit var rvPoster: RecyclerView
    private val listPotensi = ArrayList<Int>()
    private val listMinat = ArrayList<Int>()
    private val listLahanKering = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPosterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        rvPoster = binding.rvPoster
        rvPoster.layoutManager = LinearLayoutManager(this)
        listMinat.apply {
            add(R.drawable.minat1)
            add(R.drawable.minat2)
            add(R.drawable.minat3)
        }
        listPotensi.apply {
            add(R.drawable.potensi1)
            add(R.drawable.potensi2)
            add(R.drawable.potensi3)
            add(R.drawable.potensi4)
            add(R.drawable.potensi5)
        }
        listLahanKering.apply {
            add(R.drawable.lahan_kering1)
            add(R.drawable.lahan_kering2)
        }
        val poster = intent.getStringExtra("POSTER")
        when (poster) {
            "potensi" -> {
                val listPosterAdapter = ListPosterAdapter(listPotensi)
                rvPoster.adapter = listPosterAdapter
            }
            "minat" -> {
                val listPosterAdapter = ListPosterAdapter(listMinat)
                rvPoster.adapter = listPosterAdapter
            }
            "lahan_kering" -> {
                val listPosterAdapter = ListPosterAdapter(listLahanKering)
                rvPoster.adapter = listPosterAdapter
            }
        }


    }
}

class ListPosterAdapter(private val list: ArrayList<Int>) :
    RecyclerView.Adapter<ListPosterAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)

        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val res = list[position]
        Log.i("photoURL:", res.toString())
        Glide.with(holder.itemView.context)
            .load(res) // URL Gambar
            .fitCenter()
            .into(holder.ivImage) // imageView mana yang akan diterapkan
    }

    override fun getItemCount(): Int = list.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView = itemView.findViewById(R.id.iv_item_poster)
    }

}