package com.example.ecomarket.domain

import com.example.ecomarket.data.entity.CategoryListItem

interface CategoryListUseCase {

    suspend fun executeCategoryList(): List<CategoryListItem>
}