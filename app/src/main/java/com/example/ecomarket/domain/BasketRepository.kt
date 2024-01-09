package com.example.ecomarket.domain

import androidx.lifecycle.LiveData
import com.example.ecomarket.data.entity.ProductListItem

interface BasketRepository {

    suspend fun addProduct(product: ProductListItem)
    suspend fun addProducts(products: List<ProductListItem>)
    fun getAllBasketProduct(): LiveData<List<ProductListItem>>
    suspend fun deleteProduct(productId: Int)
    suspend fun getBasketProductById(productId: Int): ProductListItem?
    suspend fun incrementProductQuantity(productId: Int)
    suspend fun decrementProductQuantity(productId: Int)

    fun basketItemGreaterThanZero(): LiveData<List<ProductListItem>>
}