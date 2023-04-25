package com.yumtaufikhidayat.jmo.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yumtaufikhidayat.jmo.databinding.ItemOtherServiceBinding
import com.yumtaufikhidayat.jmo.model.home.ServiceProgram

class OtherServiceAdapter : ListAdapter<ServiceProgram, OtherServiceAdapter.ViewHolder>(serviceProgramDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemOtherServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemOtherServiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ServiceProgram) {
            binding.apply {
                imgOtherService.setImageDrawable(ContextCompat.getDrawable(itemView.context, data.serviceProgramIcon))
                tvOtherService.text = data.serviceProgramTitle
            }
        }
    }

    companion object {
        private val serviceProgramDiffCallback = object : DiffUtil.ItemCallback<ServiceProgram>(){
            override fun areItemsTheSame(
                oldItem: ServiceProgram,
                newItem: ServiceProgram
            ): Boolean = oldItem.serviceProgramTitle == newItem.serviceProgramTitle

            override fun areContentsTheSame(
                oldItem: ServiceProgram,
                newItem: ServiceProgram
            ): Boolean = oldItem == newItem
        }
    }
}