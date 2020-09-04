package org.pradd.cookingbook.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import org.pradd.cookingbook.R
import org.pradd.cookingbook.data.Recipe

class RecipesAdapter(var clickListener: OnRecipeClickListener) : RecyclerView.Adapter<RecipeViewHolder>() {

    private val items = mutableListOf<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipes_view, null)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val title = items[position].name
        val image = items[position].image
        holder.initialize(items, clickListener)
        holder.bind(title, image)
    }

    fun addItems(newItems: List<Recipe>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imageView = view.findViewById<ImageView>(R.id.recipe_image)
    private val titleText = view.findViewById<TextView>(R.id.recipe_title)

    fun initialize(items: List<Recipe>, action: OnRecipeClickListener) {
        itemView.setOnClickListener{
            action.onItemClick(items[adapterPosition], adapterPosition)
        }
    }

    fun bind(title: String?, image: String?) {
        imageView?.apply{
            load("https://cookingbook.org/images/uploads/s_$image")
        }
        titleText.text = title
    }
}

interface OnRecipeClickListener {
    fun onItemClick(item: Recipe, position: Int)
}