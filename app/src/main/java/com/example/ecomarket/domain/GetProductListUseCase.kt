package com.example.ecomarket.domain

import com.example.ecomarket.data.entity.ProductListItem

interface GetProductListUseCase {

    suspend fun executeGetProductList(): List<ProductListItem>
}