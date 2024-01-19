package com.example.ecomarket.domain

interface CategoryListUseCase {

    suspend fun executeCategoryList(): List<CategoryListItem>
}