package com.example.androidanddatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class FilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filter_items)

        val btnName = findViewById<Button>(R.id.btn_filter_name)
        val btnAge = findViewById<Button>(R.id.btn_filter_age)
        val btnBreed = findViewById<Button>(R.id.btn_filter_breed)

        btnName.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("filter", "CAT_NAME")
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        btnAge.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("filter", "CAT_AGE")
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        btnBreed.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("filter", "CAT_BREED")
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}