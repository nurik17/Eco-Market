package com.example.ecomarket.presentation.basket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.ecomarket.data.entity.ProductListItem
import com.example.ecomarket.databinding.BasketItemBinding
import com.example.ecomarket.presentation.detail.ProductDiffUtilCallBack

class BasketAdapter: ListAdapter<ProductListItem, BasketViewHolder>(ProductDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BasketItemBinding.inflate(inflater, parent, false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
