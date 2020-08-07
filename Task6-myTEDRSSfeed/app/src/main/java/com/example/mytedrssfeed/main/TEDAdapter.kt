package com.example.mytedrssfeed.main

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.mytedrssfeed.R
import com.example.mytedrssfeed.main.data.Item

class TEDAdapter(var clickListener: OnCatItemClickListener) : RecyclerView.Adapter<TEDViewHolder>() {

    private val items = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TEDViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, null)
        return TEDViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TEDViewHolder, position: Int) {
        val link = items[position].itunes_image?.url
        val title = items[position].title
        val description = items[position].description
        val duration = items[position].duration
        holder.initialize(items, clickListener)
        holder.bind(link, title, description, duration)
    }

    fun addItems(newItems: List<Item>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

class TEDViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imageView = view.findViewById<ImageView>(R.id.image_view)
    private val titleText = view.findViewById<TextView>(R.id.text_title)
    private val descriptionText = view.findViewById<TextView>(R.id.text_descr)
    private val durationText = view.findViewById<TextView>(R.id.text_time)

    fun initialize(items: List<Item>, action: OnCatItemClickListener) {
        itemView.setOnClickListener{
            action.onItemClick(items[adapterPosition], adapterPosition)
        }
    }

    @SuppressLint("LongLogTag")
    fun bind(link: String?, title: String?, description: String?, duration: String?) {

        imageView?.apply{
            load(link) {
                size(150, 100)
            }
        }
        titleText.text = title
        descriptionText.text = description
        durationText.text = duration
    }
}

interface OnCatItemClickListener {
    fun onItemClick(item: Item, position: Int)
}