package com.example.ecomarket.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.ecomarket.domain.CategoryListItem
import com.example.ecomarket.databinding.CategoryItemBinding

class CategoryAdapter(
    private val onClick:(CategoryListItem) -> Unit
): ListAdapter<CategoryListItem, CategoryViewHolder>(DiffUtilCallBack()) {

    fun setData(categoryList: List<CategoryListItem>) {
        submitList(categoryList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,onClick)
    }
}
