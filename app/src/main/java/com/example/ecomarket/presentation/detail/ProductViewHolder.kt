package com.example.ecomarket.presentation.detail

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomarket.R
import com.example.ecomarket.data.entity.CategoryListItem
import com.example.ecomarket.data.entity.ProductListItem
import com.example.ecomarket.databinding.CategoryItemBinding
import com.example.ecomarket.databinding.ProductItemBinding

class ProductViewHolder(private val binding: ProductItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: ProductListItem, onClick:(ProductListItem) -> Unit){
        binding.apply {
            productName.text = item.title
            productPrice.text = item.price+" тг"

            Glide.with(productImage)
                .load(item.image)
                .error(R.drawable.ex_category)
                .into(productImage)
            root.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }
}