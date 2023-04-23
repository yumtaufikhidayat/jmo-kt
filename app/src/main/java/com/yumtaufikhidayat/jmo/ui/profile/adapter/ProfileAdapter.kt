package com.yumtaufikhidayat.jmo.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yumtaufikhidayat.jmo.databinding.ItemProfileMenuBinding
import com.yumtaufikhidayat.jmo.model.profile.Profile

class ProfileAdapter(
    private val onItemClickListener: (Int) -> Unit
) : ListAdapter<Profile, ProfileAdapter.ViewHolder>(profileDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProfileMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemProfileMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Profile) {
            binding.apply {
                tvProfileMenuName.apply {
                    text = data.profileMenuName
                    setTextColor(ContextCompat.getColor(itemView.context, data.profileMenuTextColor))
                    setOnClickListener {
                        onItemClickListener(layoutPosition)
                    }
                }
                imgProfileMenu.apply {
                    setImageDrawable(ContextCompat.getDrawable(itemView.context, data.profileMenuIcon))
                    setOnClickListener {
                        onItemClickListener(layoutPosition)
                    }
                }
            }
        }
    }

    companion object {
        private val profileDiffCallback = object : DiffUtil.ItemCallback<Profile>(){
            override fun areItemsTheSame(
                oldItem: Profile,
                newItem: Profile
            ): Boolean = oldItem.profileMenuName == newItem.profileMenuName

            override fun areContentsTheSame(
                oldItem: Profile,
                newItem: Profile
            ): Boolean = oldItem == newItem
        }
    }
}