package com.yumtaufikhidayat.jmo.ui.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yumtaufikhidayat.jmo.databinding.ItemNewsBinding
import com.yumtaufikhidayat.jmo.model.news.NewsArticle
import com.yumtaufikhidayat.jmo.utils.Common
import com.yumtaufikhidayat.jmo.utils.Common.convertDate
import com.yumtaufikhidayat.jmo.utils.Common.loadImage

class NewsAdapter(private val onItemClickListener: (NewsArticle) -> Unit): PagingDataAdapter<NewsArticle, NewsAdapter.ViewHolder>(newsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) holder.bind(data)
        holder.setIsRecyclable(false)
    }

    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NewsArticle) {
            binding.apply {
                imgNewsBanner.loadImage(data.urlToImage.orEmpty())
                tvNewsTitle.text = data.title.orEmpty()
                tvPublishedAt.text = data.publishedAt?.convertDate(Common.DATE_FORMAT_1).orEmpty()
                itemView.setOnClickListener {
                    onItemClickListener(data)
                }
            }
        }
    }

    companion object {
        private val newsDiffCallback = object : DiffUtil.ItemCallback<NewsArticle>(){
            override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean = oldItem.publishedAt == newItem.publishedAt
            override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean = oldItem == newItem
        }
    }
}