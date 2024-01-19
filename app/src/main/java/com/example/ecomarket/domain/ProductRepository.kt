package com.example.ecomarket.domain

interface ProductRepository {
    suspend fun getCategoryList(): List<CategoryListItem>
    suspend fun getProductList(): List<ProductListItem>

    suspend fun getHistoryOrderList(): List<OrderedItem>

    suspend fun createOrder(orderRequest: OrderRequest): OrderedItem


}