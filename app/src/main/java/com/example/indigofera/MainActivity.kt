package com.example.indigofera

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.indigofera.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvArticle: RecyclerView
    private val list_temp = mutableListOf<Article>()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        rvArticle = binding.rvArticle
        rvArticle.setHasFixedSize(true)
        rvArticle.layoutManager = LinearLayoutManager(this)
        binding.progressBar.visibility = View.VISIBLE
        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this)
        db.collection("article")
            .get()
            .addOnSuccessListener { articles ->
                for (article in articles) {
                    val title = article["title"].toString()
                    val url = article["photo-url"].toString()
                    val content = article["text"].toString()
                    val author = article["author"].toString()
                    Log.i("getdata : ", title)
                    val artc = Article(url, title, content, author)
                    list_temp.add(artc)
                }
                binding.progressBar.visibility = View.INVISIBLE
                val list = ArrayList<Article>()
                for (article in list_temp) {
                    list.add(article)
                }
                showRecyclerViewer(list)
            }
            .addOnFailureListener {
                binding.progressBar.visibility = View.INVISIBLE
                Log.w("get article", "Error getting documents: ", it)
            }
        binding.apply {
            cvPotensi.setOnClickListener {
                val intent = Intent(this@MainActivity, PosterActivity::class.java)
                intent.putExtra("POSTER", "potensi")
                startActivity(intent)
            }
            cvMinat.setOnClickListener {
                val intent = Intent(this@MainActivity, PosterActivity::class.java)
                intent.putExtra("POSTER", "minat")
                startActivity(intent)
            }
            cvLahanKering.setOnClickListener {
                val intent = Intent(this@MainActivity, PosterActivity::class.java)
                intent.putExtra("POSTER", "lahan_kering")
                startActivity(intent)

            }
        }

    }

    private fun showRecyclerViewer(list: ArrayList<Article>) {
        val listArticleAdapter = ListArticleAdapter(list)
        rvArticle.adapter = listArticleAdapter

        listArticleAdapter.setOnItemClickCallback(object : ListArticleAdapter.OnItemClickCallback {
            override fun onItemClick(data: Article) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("DATA", data)
                startActivity(intent)
            }
        })
    }

    override fun onRefresh() {
        swipeRefreshLayout.isRefreshing = true
        db.collection("article")
            .get()
            .addOnSuccessListener { articles ->
                list_temp.clear()
                for (article in articles) {
                    val title = article["title"].toString()
                    val url = article["photo-url"].toString()
                    val content = article["text"].toString()
                    val author = article["author"].toString()
                    Log.i("getdata : ", title)
                    val artc = Article(url, title, content, author)
                    list_temp.add(artc)
                }
                swipeRefreshLayout.isRefreshing = false
                val list = ArrayList<Article>()
                for (article in list_temp) {
                    list.add(article)
                }
                showRecyclerViewer(list)
            }
            .addOnFailureListener {
                swipeRefreshLayout.isRefreshing = false
                Log.w("get article", "Error getting documents: ", it)
            }

    }
}