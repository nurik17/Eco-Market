package com.example.ecomarket.utils

import com.example.ecomarket.data.remote.ProductApi
import com.example.ecomarket.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DiModule {

    @Provides
    fun provideProductApi(retrofit: RetrofitClient): ProductApi{
        return  retrofit.api
    }

    @Provides
    fun provideRetrofitClient(): RetrofitClient{
        return RetrofitClient
    }
}
