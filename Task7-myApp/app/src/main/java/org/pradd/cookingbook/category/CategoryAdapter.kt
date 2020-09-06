package org.pradd.cookingbook.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.pradd.cookingbook.R
import org.pradd.cookingbook.model.Category

class CategoryAdapter(var clickListener: OnCategoryClickListener) : RecyclerView.Adapter<CategoryViewHolder>() {

    private val items = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item_category, null)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = items[position].name
        holder.initialize(items, clickListener)
        holder.bind(category)
    }

    fun addItems(category: List<Category>) {
        items.addAll(category)
        notifyDataSetChanged()
    }
}

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nameText = view.findViewById<TextView>(R.id.category_name)

    fun initialize(items: List<Category>, action: OnCategoryClickListener) {
        itemView.setOnClickListener{
            action.onItemClick(items[adapterPosition], adapterPosition)
        }
    }

    fun bind(category: String) {
        nameText.text = category
    }
}


interface OnCategoryClickListener {
    fun onItemClick(item: Category, position: Int)
}