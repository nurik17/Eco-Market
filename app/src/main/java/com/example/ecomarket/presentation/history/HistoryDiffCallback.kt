package com.example.ecomarket.presentation.history

import androidx.recyclerview.widget.DiffUtil
import com.example.ecomarket.data.entity.OrderedItem

class HistoryDiffCallback : DiffUtil.ItemCallback<OrderedItem>() {
    override fun areItemsTheSame(oldItem: OrderedItem, newItem: OrderedItem): Boolean {
        return oldItem.order_number == newItem.order_number
    }

    override fun areContentsTheSame(oldItem: OrderedItem, newItem: OrderedItem): Boolean {
        return oldItem == newItem
    }
}