package com.example.ecomarket.di

import android.content.Context
import androidx.room.Room
import com.example.ecomarket.data.local.BasketProductDao
import com.example.ecomarket.data.local.BasketProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): BasketProductDatabase{
        return Room
            .databaseBuilder(context,BasketProductDatabase::class.java,"basket_product")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun getBasketDao(db: BasketProductDatabase): BasketProductDao = db.basketDao()
}