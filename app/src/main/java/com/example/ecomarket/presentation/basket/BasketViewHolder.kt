package com.example.ecomarket.presentation.basket

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomarket.R
import com.example.ecomarket.data.entity.ProductListItem
import com.example.ecomarket.databinding.BasketItemBinding

class BasketViewHolder(private val binding: BasketItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: ProductListItem) {

        binding.apply {
            basketProductName.text = item.title
            basketProductPrice.text = ("Цена "+item.price +"тг"+"за шт")


            Glide.with(basketProductImage)
                .load(item.image)
                .error(R.drawable.ex_basket_item)
                .into(basketProductImage)

        }
    }
}
