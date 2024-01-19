package com.example.ecomarket.domain

class CategoryListUseCaseInteraction(private val repository: ProductRepository): CategoryListUseCase {

    override suspend fun executeCategoryList(): List<CategoryListItem> {
        return repository.getCategoryList()
    }
}