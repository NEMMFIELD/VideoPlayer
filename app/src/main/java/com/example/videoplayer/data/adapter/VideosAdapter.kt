package com.example.videoplayer.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.videoplayer.R
import com.example.videoplayer.databinding.ItemViewBinding
import com.example.videoplayer.domain.models.ModelDTO

class VideosAdapter(private val listener: ClickListener) :
    ListAdapter<ModelDTO, VideosAdapter.ViewHolder>(VideosDiffUtil()) {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemViewBinding.bind(view)
        fun bind(item: ModelDTO, listener: ClickListener) {
            with(binding) {
                itemPoster.load(item.posterUrl)
                itemView.setOnClickListener {
                    listener.onItemCLick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class VideosDiffUtil : DiffUtil.ItemCallback<ModelDTO>() {
        override fun areItemsTheSame(oldItem: ModelDTO, newItem: ModelDTO): Boolean {
            return oldItem.posterUrl == newItem.posterUrl
        }

        override fun areContentsTheSame(oldItem: ModelDTO, newItem: ModelDTO): Boolean {
            return oldItem == newItem
        }

    }

    interface ClickListener {
        fun onItemCLick(modelDTO: ModelDTO)
    }
}