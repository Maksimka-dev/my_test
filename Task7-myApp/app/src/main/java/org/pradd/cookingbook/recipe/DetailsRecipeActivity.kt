package org.pradd.cookingbook.recipe

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import kotlinx.android.synthetic.main.activity_details_recipe.*
import org.pradd.cookingbook.R
import org.pradd.cookingbook.data.Ingredients
import org.pradd.cookingbook.data.Recipe

class DetailsRecipeActivity: AppCompatActivity() {

    private val itemAdapter = DetailsRecipeAdapter()
    private val mPresenter = DetailsRecipePresenter(this)
    private lateinit var mRecipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_recipe)

        val arguments = intent.extras
        val recipeId = arguments?.getString("id")

        recycler_view_recipe.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@DetailsRecipeActivity)
        }

        recipeId?.let {
            mPresenter.getItems(it)
        }
    }
    fun setRecipe(recipe: Recipe) {
        mRecipe = recipe
        findViewById<TextView>(R.id.title_recipe).text = mRecipe.name
        findViewById<ImageView>(R.id.img_recipe)?.apply{
            load("https://cookingbook.org/images/uploads/m_${mRecipe.image}")
        }
        val body = findViewById<TextView>(R.id.body_recipe)
        body.text = Html.fromHtml(mRecipe.text, Html.FROM_HTML_MODE_LEGACY)
    }

    fun setIngredients(items: List<Ingredients>?) {
        itemAdapter.addItems(items)
    }
}