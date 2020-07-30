package com.example.mytedrssfeed.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytedrssfeed.R
import com.example.mytedrssfeed.main.data.Item
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity(presenter: MainPresenter) : AppCompatActivity(), OnCatItemClickListener, MainView {

    private val itemAdapter = TEDAdapter(this)
    private val mPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        mPresenter.start()
    }

    override fun onItemClick(item: Item, position: Int) {
        //TODO: create new Activity or Fragment
    }

    override fun setItems(items: List<Item>) {
        itemAdapter.addItems(items)
    }

}