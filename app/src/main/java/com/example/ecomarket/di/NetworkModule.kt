package com.example.ecomarket.di

import android.os.Build.VERSION_CODES.BASE
import com.example.ecomarket.data.remote.ProductApi
import com.example.ecomarket.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @EcoMarketUrl
    fun provideEcoMarketUrl(): String {
        return Constant.BASE_URL
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    @EcoMarketUrl
    @Provides
    @Singleton
    fun getProductRetrofit(@EcoMarketUrl url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun getProductApi(@EcoMarketUrl retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }
}
@Qualifier
annotation class EcoMarketUrl