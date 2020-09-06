package org.pradd.cookingbook.recipe

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.pradd.cookingbook.R
import org.pradd.cookingbook.model.Ingredients

class DetailsRecipeAdapter : RecyclerView.Adapter<RecipeViewHolder>() {

    private val items = mutableListOf<Ingredients>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item_ingredient, null)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val ingredient = items[position].name
        holder.bind(ingredient)
    }

    fun addItems(ingredients: List<Ingredients>?) {
        ingredients?.let { items.addAll(it) }
        notifyDataSetChanged()
    }
}

class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val titleText = view.findViewById<TextView>(R.id.ingredient_recipe)

    @SuppressLint("SetTextI18n")
    fun bind(name: String) {
        titleText.text = "- $name"
    }
}