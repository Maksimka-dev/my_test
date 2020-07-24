package com.example.thecatapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.thecatapi.R
import com.example.thecatapi.data.Cat

class MyAdapter(var clickListener: OnCatItemClickListener) : RecyclerView.Adapter<CatViewHolder>() {

    private val items = mutableListOf<Cat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, null)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val imageUrl = items[position].image
        val id = items[position].id
        holder.initialize(items, clickListener)
        holder.bind(imageUrl, id)
    }

    fun addItems(newItems: List<Cat>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imageView = view.findViewById<ImageView>(R.id.imageView)

    fun initialize(items: List<Cat>, action: OnCatItemClickListener) {
        itemView.setOnClickListener{
            action.onItemClick(items[adapterPosition].image, adapterPosition)
        }
    }

    fun bind(imageUrl: String, id: String) {
        imageView.load(imageUrl) {
            size(600)
            setParameter("imageId", id)
        }
    }
}

interface OnCatItemClickListener {
    fun onItemClick(url: String, position: Int)
}