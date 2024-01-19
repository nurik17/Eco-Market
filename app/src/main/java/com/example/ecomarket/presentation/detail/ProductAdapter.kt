package com.example.ecomarket.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.ecomarket.domain.ProductListItem
import com.example.ecomarket.databinding.ProductItemBinding

class ProductAdapter(
    private val onClick:(clickableView: ProductViewHolder.ClickableView, item: ProductListItem) -> Unit,
): ListAdapter<ProductListItem, ProductViewHolder>(ProductDiffUtilCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,onClick)
    }
}
