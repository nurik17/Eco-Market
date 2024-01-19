package com.example.ecomarket.domain

interface GetProductListUseCase {

    suspend fun executeGetProductList(): List<ProductListItem>
}