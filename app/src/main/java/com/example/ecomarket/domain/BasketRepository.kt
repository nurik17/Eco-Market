package com.example.ecomarket.domain

import androidx.lifecycle.LiveData

interface BasketRepository {

    suspend fun addProducts(products: List<ProductListItem>)
    fun getAllBasketProduct(): LiveData<List<ProductListItem>>
    suspend fun deleteProduct(productId: Int)
    suspend fun deleteAllProducts()
    suspend fun incrementProductQuantity(productId: Int)
    suspend fun decrementProductQuantity(productId: Int)

    fun basketItemGreaterThanZero(): LiveData<List<ProductListItem>>
}