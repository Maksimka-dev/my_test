package org.pradd.cookingbook.user

import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_details_recipe.*
import org.pradd.cookingbook.R
import org.pradd.cookingbook.model.Ingredients
import org.pradd.cookingbook.model.Recipe
import org.pradd.cookingbook.recipe.DetailsRecipeAdapter

class UserDetailsRecipeActivity: AppCompatActivity() {

    private val itemAdapter = DetailsRecipeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_recipe)

        val arguments = intent.extras
        val recipeJson = arguments?.getString("recipe")
        val gson = Gson()
        if (recipeJson != null) {
            val mRecipe = gson.fromJson(recipeJson, Recipe::class.java)
            setRecipe(mRecipe)
            setIngredients(mRecipe.ingredients)
        }

        recycler_view_recipe.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@UserDetailsRecipeActivity)
        }
    }

    private fun setRecipe(recipe: Recipe) {
        findViewById<TextView>(R.id.title_recipe).text = recipe.name
//        findViewById<ImageView>(R.id.img_recipe)?.apply{
//            load("https://cookingbook.org/images/uploads/m_${mRecipe.image}")
//        }
        val body = findViewById<TextView>(R.id.body_recipe)
        body.text = Html.fromHtml(recipe.text, Html.FROM_HTML_MODE_LEGACY)
    }

    private fun setIngredients(items: List<Ingredients>?) {
        itemAdapter.addItems(items)
    }
}