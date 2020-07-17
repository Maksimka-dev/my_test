package com.example.androidanddatabase.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidanddatabase.R
import com.example.androidanddatabase.lib.Cat

class MyRecycleAdapter(listDB: List<Cat>) :
    RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder>() {

    private val mListDB: List<Cat> = listDB

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView? = null
        init {
            tvName = itemView.findViewById(R.id.card_item)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return MyViewHolder(textView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName?.text = """Name: ${mListDB[position].name}
                            |Age: ${mListDB[position].age}
                            |Breed: ${mListDB[position].breed}""".trimMargin()
    }

    override fun getItemCount() = mListDB.size
}