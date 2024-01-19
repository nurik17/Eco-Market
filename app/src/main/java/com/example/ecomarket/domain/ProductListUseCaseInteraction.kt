package com.example.ecomarket.domain

class ProductListUseCaseInteraction(
    private val repository: ProductRepository
) :GetProductListUseCase {

    override suspend fun executeGetProductList(): List<ProductListItem> {
        return repository.getProductList()
    }
}