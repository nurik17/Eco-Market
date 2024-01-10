package com.example.ecomarket.di

import com.example.ecomarket.domain.CategoryListUseCase
import com.example.ecomarket.domain.CategoryListUseCaseInteraction
import com.example.ecomarket.domain.GetProductListUseCase
import com.example.ecomarket.domain.OrderedListItemInteraction
import com.example.ecomarket.domain.OrderedListItemsUseCase
import com.example.ecomarket.domain.ProductListUseCaseInteraction
import com.example.ecomarket.domain.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideProductUseCase(repo: ProductRepository): CategoryListUseCase =
        CategoryListUseCaseInteraction(repo)

    @Provides
    fun provideProductListUseCase(repo: ProductRepository): GetProductListUseCase =
        ProductListUseCaseInteraction(repo)

    @Provides
    fun provideOrderHistoryListUseCase(repo: ProductRepository): OrderedListItemsUseCase =
        OrderedListItemInteraction(repo)
}