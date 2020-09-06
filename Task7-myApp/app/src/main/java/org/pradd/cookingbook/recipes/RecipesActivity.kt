package org.pradd.cookingbook.recipes

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recipes.*
import org.pradd.cookingbook.R
import org.pradd.cookingbook.model.Recipe
import org.pradd.cookingbook.recipe.DetailsRecipeActivity

class RecipesActivity: AppCompatActivity(),
    OnRecipeClickListener {

    private val itemAdapter = RecipesAdapter(this)
    private val mPresenter = RecipesPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        val arguments = intent.extras
        val title = findViewById<TextView>(R.id.text_title)
        title.text = getTitle(arguments?.getString("name"))

        recycler_view.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@RecipesActivity)
        }

        mPresenter.getItems(arguments?.getInt("categoryId"))
    }

    override fun onItemClick(item: Recipe, position: Int) {
        goActivityRecipe(item.id)
    }

    fun setItems(items: List<Recipe>) {
        itemAdapter.addItems(items)
    }

    private fun getTitle(title: String?): String = when (title) {
            "breakfast" -> getString(R.string.breakfast)
            "lunch" -> getString(R.string.lunch)
            "dessert" -> getString(R.string.dessert)
            "dinner" -> getString(R.string.dinner)
            "all_recipes" -> getString(R.string.all_recipes)
            else -> title.toString()
    }

    private fun goActivityRecipe(id: Long) {
        val intent = Intent(this, DetailsRecipeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}