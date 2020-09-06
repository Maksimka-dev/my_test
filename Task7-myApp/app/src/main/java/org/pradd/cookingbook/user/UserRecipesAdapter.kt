package org.pradd.cookingbook.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.pradd.cookingbook.R
import org.pradd.cookingbook.model.Recipe

class UserRecipesAdapter(private val clickListener: OnUserRecipeClickListener) : RecyclerView.Adapter<UserRecipeViewHolder>() {

    private val items = mutableListOf<Recipe>()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipes_view, null)
        return UserRecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UserRecipeViewHolder, position: Int) {
        val title = items[position].name
        holder.initialize(items, clickListener)
        holder.bind(title)
    }

    fun addItems(newItems: List<Recipe>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

class UserRecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val titleText = view.findViewById<TextView>(R.id.recipe_title)

    fun initialize(items: List<Recipe>, action: OnUserRecipeClickListener) {
        itemView.setOnClickListener{
            action.onItemClick(items[adapterPosition], adapterPosition)
        }
    }

    fun bind(title: String?) {
        titleText.text = title
    }
}

interface OnUserRecipeClickListener {
    fun onItemClick(item: Recipe, position: Int)
}