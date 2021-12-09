package com.example.fakenews.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fakenews.R
import com.example.fakenews.databinding.ItemLayoutBinding

class NewsViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: ItemLayoutBinding by viewBinding()

    companion object {
        fun from(parent: ViewGroup): NewsViewHolder {
            return NewsViewHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_layout, parent, false)
            )
        }
    }

    private val titleTextView: TextView by lazy { binding.titleTextView }
    private val authorTextView: TextView by lazy { binding.authorTextView }
    private val dateTextView: TextView by lazy { binding.dateTextView }
    private val topicTextView: TextView by lazy { binding.topicTextView }
    private val textTextView: TextView by lazy { binding.textTextView }

    private var itemClickListener: ((News) -> Unit)? = null

    fun bindView(item: News) {
        titleTextView.text = item.title
        authorTextView.text = item.author
        dateTextView.text = item.date
        topicTextView.text = item.topic
        textTextView.text = item.text

        itemView.setOnClickListener {

            itemClickListener?.invoke(item)
        }
    }

    fun setItemClickListener(listener: ((News) -> Unit)) {
        itemClickListener = listener
    }
}