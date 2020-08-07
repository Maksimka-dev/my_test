package com.example.mytedrssfeed.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytedrssfeed.R
import com.example.mytedrssfeed.main.data.Item
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(), OnCatItemClickListener, MainView {

    private val itemAdapter = TEDAdapter(this)
    private val mPresenter = MainPresenter(this)

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
        val intent = Intent(this, NewActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("title", "${item.title}")
        intent.putExtra("desc", "${item.description}")
        intent.putExtra("link", "${item.group?.contentList?.get(3)?.url}")
        startActivity(intent)
    }

    override fun setItems(items: List<Item>) {
        itemAdapter.addItems(items)
    }

}