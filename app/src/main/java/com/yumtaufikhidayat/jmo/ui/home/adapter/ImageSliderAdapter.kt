package com.yumtaufikhidayat.jmo.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yumtaufikhidayat.jmo.R
import com.yumtaufikhidayat.jmo.databinding.ItemSlideBinding
import com.yumtaufikhidayat.jmo.model.home.ImageSlider

class ImageSliderAdapter : ListAdapter<ImageSlider, ImageSliderAdapter.ViewHolder>(imageSliderDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSlideBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemSlideBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ImageSlider) {
            Glide.with(itemView.context)
                .load(data.imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imgSlider)
        }
    }

    companion object {
        val imageSliderDiffCallback = object : DiffUtil.ItemCallback<ImageSlider>(){
            override fun areItemsTheSame(
                oldItem: ImageSlider,
                newItem: ImageSlider
            ): Boolean = oldItem.imageUrl == newItem.imageUrl

            override fun areContentsTheSame(
                oldItem: ImageSlider,
                newItem: ImageSlider
            ): Boolean = oldItem == newItem
        }
    }
}