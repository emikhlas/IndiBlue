package com.example.indigofera

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class ListArticleAdapter(private val listArticle: ArrayList<Article>) :
    RecyclerView.Adapter<ListArticleAdapter.ListViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)

        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (url,title,text,author) = listArticle[position]
        holder.tvTitle.text = title
        holder.tvAuthor.text = author
        Glide.with(holder.itemView.context)
            .load(url) // URL Gambar
            .centerCrop()
            .transform(RoundedCorners( 10))
            .into(holder.ivImage) // imageView mana yang akan diterapkan

        holder.itemView.setOnClickListener{onItemClickCallback.onItemClick(listArticle[holder.adapterPosition])}
    }

    override fun getItemCount(): Int = listArticle.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        val ivImage: ImageView = itemView.findViewById(R.id.iv_item_article)
        val tvAuthor: TextView = itemView.findViewById(R.id.tv_item_author)
    }

    interface OnItemClickCallback{
        fun onItemClick(data: Article)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}