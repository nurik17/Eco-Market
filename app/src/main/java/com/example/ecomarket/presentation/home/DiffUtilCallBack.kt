package com.example.ecomarket.presentation.home

import androidx.recyclerview.widget.DiffUtil
import com.example.ecomarket.data.entity.CategoryListItem

class DiffUtilCallBack : DiffUtil.ItemCallback<CategoryListItem>() {
    override fun areItemsTheSame(oldItem: CategoryListItem, newItem: CategoryListItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CategoryListItem, newItem: CategoryListItem): Boolean {
        return oldItem.id == newItem.id
    }
}