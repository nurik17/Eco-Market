package com.example.ecomarket.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ecomarket.data.entity.ProductListItemDbModel

@Database(entities = [ProductListItemDbModel::class], version = 1)
abstract class BasketProductDatabase: RoomDatabase() {
    abstract fun basketDao(): BasketProductDao
}