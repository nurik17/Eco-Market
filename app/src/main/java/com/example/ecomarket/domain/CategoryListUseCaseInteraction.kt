package com.example.ecomarket.domain

import com.example.ecomarket.data.entity.CategoryListItem

class CategoryListUseCaseInteraction(private val repository: ProductRepository): CategoryListUseCase {

    override suspend fun executeCategoryList(): List<CategoryListItem> {
        return repository.getCategoryList()
    }
}