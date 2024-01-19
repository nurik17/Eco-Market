package com.example.ecomarket.presentation.detail

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomarket.R
import com.example.ecomarket.domain.ProductListItem
import com.example.ecomarket.databinding.ProductItemBinding

class ProductViewHolder(private val binding: ProductItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(
        item: ProductListItem,
        onClick: (clickableView: ClickableView, item: ProductListItem) -> Unit
    ) {
        binding.apply {
            productName.text = item.title
            productPrice.text = item.price + " тг"

            Glide.with(productImage)
                .load(item.image)
                .error(R.drawable.ex_category)
                .into(productImage)



            if (item.quantity > 1) {
                showQuantityAndButtons(item)
            } else {
                hideQuantityAndButtons(onClick,item)
            }

            productAddBtn.setOnClickListener {
                onClick(ClickableView.ONADDCLICK, item)
                productAddBtn.isSelected != productAddBtn.isSelected
            }
            icMinus.setOnClickListener {
                onClick(ClickableView.ONMINUSCLICK, item)
            }
            icPlus.setOnClickListener {
                onClick(ClickableView.ONPLUSCLICK, item)
            }
            root.setOnClickListener {
                onClick.invoke(ClickableView.ONCLICK, item)
            }
        }
    }

    private fun hideQuantityAndButtons(
        onClick: (clickableView: ClickableView, item: ProductListItem) -> Unit,
        item: ProductListItem
    ) {
        binding.apply {
            quantityProduct.visibility = View.GONE
            icMinus.visibility = View.GONE
            icPlus.visibility = View.GONE

            productAddBtn.visibility = View.VISIBLE
            productAddBtn.setOnClickListener {
                onClick(ClickableView.ONADDCLICK, item)
            }
        }
    }

    private fun showQuantityAndButtons(item: ProductListItem) {
        binding.apply {
            quantityProduct.visibility = View.VISIBLE
            icMinus.visibility = View.VISIBLE
            icPlus.visibility = View.VISIBLE

            quantityProduct.text = item.quantity.toString()

            productAddBtn.visibility = View.GONE
        }
    }

    enum class ClickableView {
        ONCLICK,
        ONADDCLICK,
        ONMINUSCLICK,
        ONPLUSCLICK
    }
}