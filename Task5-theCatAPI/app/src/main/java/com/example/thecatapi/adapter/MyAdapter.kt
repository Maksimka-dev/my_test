package com.example.thecatapi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thecatapi.R
import com.example.thecatapi.data.Cat

class MyAdapter(listDB: List<Cat>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        private val mListDB: List<Cat> = listDB

        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var mName: TextView? = null
            init {
                mName = itemView.findViewById(R.id.name_cat)
            }
            var mImage: TextView? = null
            init {
                mImage = itemView.findViewById(R.id.image_cat)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
            return MyViewHolder(textView)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.mName?.text = mListDB[position].name
            holder.mImage?.text = mListDB[position].image
        }

        override fun getItemCount() = mListDB.size
}