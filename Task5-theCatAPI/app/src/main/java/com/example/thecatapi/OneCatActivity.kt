package com.example.thecatapi

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.api.load

class OneCatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_cat)

        val arguments = intent.extras
        val imageView = findViewById<ImageView>(R.id.imageCatView)

        if (arguments != null) {
            val imageUrl: String? = arguments.getString("url")
            imageView.load(imageUrl)
        }
    }
}