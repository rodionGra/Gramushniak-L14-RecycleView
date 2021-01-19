package com.a6acdhmwrecycleviewfamily

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecycleAdapter(private val list: List<Person>)
    : RecyclerView.Adapter<CustomRecycleAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position].name, list[position].age, list[position].generation)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(name: String, age: Int, generation: Int) {
            itemView.apply {
                findViewById<TextView>(R.id.name).text = name
                findViewById<LinearLayout>(R.id.person_item).setPadding(generation * 40, 0, 0, 0)
                findViewById<TextView>(R.id.age).text = age.toString()
            }
        }
    }
}