package com.mtjin.bungsegwon.views.enroll_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.bungsegwon.R
import com.mtjin.bungsegwon.databinding.ItemMenuBinding
import com.mtjin.bungsegwon.model.Menu

class EnrollMenuAdapter(
    private val itemClick: (Menu) -> Unit
) : ListAdapter<Menu, EnrollMenuAdapter.ViewHolder>(
    diffUtil
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMenuBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_menu,
            parent,
            false
        )
        val viewHolder = ViewHolder(binding)
        binding.apply {
            root.setOnClickListener {
                itemClick(getItem(viewHolder.adapterPosition))
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Menu) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Menu>() {
            override fun areContentsTheSame(oldItem: Menu, newItem: Menu) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Menu, newItem: Menu) =
                oldItem.id == newItem.id
        }
    }
}