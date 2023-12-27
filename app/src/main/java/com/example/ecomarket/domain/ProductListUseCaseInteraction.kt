package com.example.ecomarket.domain

import com.example.ecomarket.data.entity.ProductListItem

class ProductListUseCaseInteraction(
    private val repository: ProductRepository
) :GetProductListUseCase {

    override suspend fun executeGetProductList(): List<ProductListItem> {
        return repository.getProductList()
    }
}