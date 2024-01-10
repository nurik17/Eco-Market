package com.example.ecomarket.domain

import com.example.ecomarket.data.entity.CategoryListItem
import com.example.ecomarket.data.entity.OrderedItem
import com.example.ecomarket.data.entity.ProductListItem

interface ProductRepository {
    suspend fun getCategoryList(): List<CategoryListItem>
    suspend fun getProductList(): List<ProductListItem>

    suspend fun getHistoryOrderList(): List<OrderedItem>
}