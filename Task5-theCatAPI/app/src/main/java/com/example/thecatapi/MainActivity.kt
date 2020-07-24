package com.example.thecatapi

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thecatapi.adapter.MyAdapter
import com.example.thecatapi.adapter.OnCatItemClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnCatItemClickListener {

    private val itemAdapter = MyAdapter(this)
    private val mLayoutManager = LinearLayoutManager(this@MainActivity)
    private val catViewModel by viewModels<CatViewModel>()
    private var loading = true
    private var previousTotal = 0
    private var visibleThreshold = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            adapter = itemAdapter
            layoutManager = mLayoutManager
        }

        catViewModel.items.observe(this, Observer {
            it ?: return@Observer
            itemAdapter.addItems(it)
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = recyclerView.childCount
                val totalItemCount = mLayoutManager.itemCount
                val firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition()
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }
                if (!loading && totalItemCount - visibleItemCount
                    <= firstVisibleItem + visibleThreshold
                ) {
                    catViewModel.addNextCats(totalItemCount/10)
                    loading = true
                }
            }
        })
    }

    override fun onItemClick(url: String, position: Int) {
        val intent = Intent(this, OneCatActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("url", url)
        startActivity(intent)
    }
}