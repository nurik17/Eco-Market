package com.example.ecomarket.presentation.detail

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomarket.R
import com.example.ecomarket.data.entity.ProductListItem
import com.example.ecomarket.databinding.ProductItemBinding

class ProductViewHolder(private val binding: ProductItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(
        item: ProductListItem,
        onClick: (clickableView: ClickableView,item: ProductListItem) -> Unit
    ) {
        binding.apply {
            productName.text = item.title
            productPrice.text = item.price + " тг"

            Glide.with(productImage)
                .load(item.image)
                .error(R.drawable.ex_category)
                .into(productImage)

            binding.productAddBtn.setOnClickListener {
                onClick(ClickableView.ONADDCLICK, item)
                binding.productAddBtn.isSelected != binding.productAddBtn.isSelected
            }
            root.setOnClickListener {
                onClick.invoke(ClickableView.ONCLICK,item)
            }
        }
    }
}

enum class ClickableView {
    ONCLICK,
    ONADDCLICK
}