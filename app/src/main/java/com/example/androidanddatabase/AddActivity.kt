package com.example.androidanddatabase

import android.content.Intent
import android.database.SQLException
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.androidanddatabase.db.DatabaseHelper
import android.util.Log

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_item_to_db)

        val database = DatabaseHelper(this)

        findViewById<Button>(R.id.add_button).setOnClickListener {
            val name = findViewById<EditText>(R.id.name_cat).text.toString()
            val age = Integer.parseInt(findViewById<EditText>(R.id.age_cat).text.toString())
            val breed = findViewById<EditText>(R.id.breed_cat).text.toString()
            try {
                database.insertQuery(name, age, breed)
            } catch (exception: SQLException) {
                Log.e("E", exception.toString())
            } finally {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}