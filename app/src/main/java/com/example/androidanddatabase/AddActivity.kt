package com.example.androidanddatabase

import android.content.Intent
import android.database.SQLException
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidanddatabase.db.DatabaseHelper


class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_item_to_db)

        val database = DatabaseHelper(this)

        val textName = findViewById<EditText>(R.id.name_cat)
        val intAge = findViewById<EditText>(R.id.age_cat)
        val textBreed = findViewById<EditText>(R.id.breed_cat)
        var isName = false
        var isAge = false
        var isBreed = false

        textName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (count > 0) isName = true
            }
        })
        intAge.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (count > 0) isAge = true
            }
        })
        textBreed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (count > 0) isBreed = true
            }
        })

        findViewById<Button>(R.id.add_button).setOnClickListener {
            if (isName && isAge && isBreed) {
                try {
                    database.insertQuery(
                        textName.text.toString(),
                        Integer.parseInt(intAge.text.toString()),
                        textBreed.text.toString()
                    )
                } catch (exception: SQLException) {
                    Log.e("E", exception.toString())
                } finally {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this,"Required fields!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}