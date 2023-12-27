package com.example.ecomarket.di

import com.example.ecomarket.data.remote.ProductApi
import com.example.ecomarket.data.remote.ProductRepositoryImpl
import com.example.ecomarket.domain.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(api: ProductApi): ProductRepository = ProductRepositoryImpl(api)
}