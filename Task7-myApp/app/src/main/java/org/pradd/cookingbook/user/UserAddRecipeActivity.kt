package org.pradd.cookingbook.user

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pradd.cookingbook.App
import org.pradd.cookingbook.R
import org.pradd.cookingbook.model.Ingredients
import org.pradd.cookingbook.model.Recipe


class UserAddRecipeActivity: AppCompatActivity() {

    private val listIngredient = mutableListOf<Ingredients>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_add_recipe)

        findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            finish()
        }

        val title = findViewById<EditText>(R.id.et_add_recipe_title)
        val description = findViewById<EditText>(R.id.et_add_recipe_description)
        val body = findViewById<EditText>(R.id.et_add_recipe_body)

        findViewById<Button>(R.id.btn_save).setOnClickListener {
            if (title.text.isNotEmpty() and body.text.isNotEmpty()) {
                val recipe = Recipe()
                recipe.description = description.text.toString()
                recipe.text = title.text.toString()
                recipe.text = body.text.toString()
                recipe.image = "image"
                recipe.ingredients = listIngredient
                CoroutineScope(Dispatchers.IO).launch {
                    App.instance?.recipeDao?.insert(recipe)
                }
                finish()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Введите название и текст рецепта",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}