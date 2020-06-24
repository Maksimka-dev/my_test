package com.example.androidanddatabase

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidanddatabase.adapter.MyRecycleAdapter
import com.example.androidanddatabase.db.DatabaseHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort -> {
                val intent = Intent(this, FilterActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        
        val database = DatabaseHelper(this)

        val arguments = intent.extras
        val listData = if (arguments != null)
            database.getListOfTopics(arguments.getString("filter"))
        else
            database.getListOfTopics()

        val recycleView: RecyclerView = findViewById(R.id.my_recycler_view)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = MyRecycleAdapter(listData)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

}