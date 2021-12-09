package com.example.fakenews.presentation.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    private var items: List<News> = mutableListOf()

    private var itemClickListener: ((News) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val viewHolder = NewsViewHolder.from(parent)

        viewHolder.setItemClickListener { chat ->
            itemClickListener?.invoke(chat)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount() = items.size

    fun submitList(data: List<News>) {
        items = data
        notifyDataSetChanged()
    }
}