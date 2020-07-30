package com.example.mytedrssfeed.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytedrssfeed.R
import com.example.mytedrssfeed.main.data.Item

class TEDAdapter(var clickListener: OnCatItemClickListener) : RecyclerView.Adapter<CatViewHolder>() {

    private val items = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, null)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val link = items[position].link
        val title = items[position].title
        val description = items[position].description
        val pubDate = items[position].pubDate
        holder.initialize(items, clickListener)
        holder.bind(link, title, description, pubDate)
    }

    fun addItems(newItems: List<Item>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val videoView = view.findViewById<VideoView>(R.id.videoView)
    private val titleText = view.findViewById<TextView>(R.id.text_title)
    private val descriptionText = view.findViewById<TextView>(R.id.text_descr)

    fun initialize(items: List<Item>, action: OnCatItemClickListener) {
        itemView.setOnClickListener{
            action.onItemClick(items[adapterPosition], adapterPosition)
        }
    }

    fun bind(link: String?, title: String?, description: String?, pubDate: String?) {
        videoView.setVideoPath(link)
        titleText.text = title
        descriptionText.text = description
    }
}

interface OnCatItemClickListener {
    fun onItemClick(item: Item, position: Int)
}