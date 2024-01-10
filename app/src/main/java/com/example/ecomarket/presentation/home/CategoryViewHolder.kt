package com.example.ecomarket.presentation.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomarket.R
import com.example.ecomarket.data.entity.CategoryListItem
import com.example.ecomarket.databinding.CategoryItemBinding

class CategoryViewHolder(private val binding: CategoryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CategoryListItem,onClick:(CategoryListItem) -> Unit){
        binding.apply {
            categoryName.text = item.name

            Glide.with(categoryImage)
                .load(item.image)
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.ex_category)
                .into(categoryImage)
            root.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }
}