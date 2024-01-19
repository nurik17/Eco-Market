package com.example.ecomarket.presentation.detail

import androidx.recyclerview.widget.DiffUtil
import com.example.ecomarket.domain.ProductListItem

class ProductDiffUtilCallBack : DiffUtil.ItemCallback<ProductListItem>() {
    override fun areItemsTheSame(oldItem: ProductListItem, newItem: ProductListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductListItem, newItem: ProductListItem): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: ProductListItem, newItem: ProductListItem): Any? {
        return if (oldItem.quantity != newItem.quantity) true else null
    }
}