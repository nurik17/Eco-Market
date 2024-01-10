package com.example.ecomarket.presentation.basket

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomarket.R
import com.example.ecomarket.data.entity.ProductListItem
import com.example.ecomarket.databinding.BasketItemBinding
import com.example.ecomarket.presentation.detail.ProductViewHolder

class BasketViewHolder(private val binding: BasketItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(
        item: ProductListItem,
        onClick: (clickableView: ClickableView, item: ProductListItem) -> Unit
    ) {

        binding.apply {
            basketProductName.text = item.title
            basketProductPrice.text = ("Цена " + item.price + " тг" + " за шт")
            quantityProduct.text = item.quantity.toString()
            basketProductTotalPrice.text = (item.price.toDouble() * item.quantity).toString() + " тг"

            Glide.with(basketProductImage)
                .load(item.image)
                .error(R.drawable.ex_basket_item)
                .into(basketProductImage)


            if (item.quantity > 0) {
                showQuantityAndButtons(item)
            } else {
                hideQuantityAndButtons(onClick, item)
            }
            icMinus.setOnClickListener {
                onClick(ClickableView.ONMINUSCLICK, item)
            }
            icPlus.setOnClickListener {
                onClick(ClickableView.ONPLUSCLICK, item)
            }
            deleteProductBlock.setOnClickListener{
                onClick(ClickableView.ONDELETECLICK,item)
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

        }
    }

    private fun showQuantityAndButtons(item: ProductListItem) {
        binding.apply {
            quantityProduct.visibility = View.VISIBLE
            icMinus.visibility = View.VISIBLE
            icPlus.visibility = View.VISIBLE
            quantityProduct.text = item.quantity.toString()
        }
    }

    enum class ClickableView {
        ONMINUSCLICK,
        ONPLUSCLICK,
        ONDELETECLICK
    }
}
