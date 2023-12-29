package com.example.ecomarket.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecomarket.data.entity.ProductListItem

@Dao
interface BasketProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: ProductListItem)

    @Query("SELECT * FROM basket_product")
    fun getAllBasketProducts(): LiveData<List<ProductListItem>>

    @Query("DELETE FROM basket_product WHERE id = :productId")
    suspend fun deleteProduct(productId: Int)

    @Query("SELECT * FROM basket_product WHERE id = :productId")
    suspend fun getBasketProductById(productId: Int): ProductListItem?

    @Query("UPDATE basket_product SET quantity = quantity + 1 WHERE id = :productId")
    suspend fun incrementProductQuantity(productId: Int)

    @Query("UPDATE basket_product SET quantity = quantity - 1 WHERE id = :productId AND quantity > 0")
    suspend fun decrementProductQuantity(productId: Int)
}