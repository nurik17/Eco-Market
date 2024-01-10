package com.example.ecomarket.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecomarket.data.entity.ProductListItem

@Dao
interface BasketProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(product: ProductListItem)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProducts(products: List<ProductListItem>)

    @Query("SELECT * FROM basket_product")
    fun getAllBasketProducts(): LiveData<List<ProductListItem>>

    @Query("DELETE FROM basket_product WHERE id = :productId")
    suspend fun deleteProduct(productId: Int)

    @Query("DELETE FROM basket_product")
    suspend fun deleteAllProducts()

    @Query("SELECT * FROM basket_product WHERE id = :productId")
    suspend fun getBasketProductById(productId: Int): ProductListItem?

    @Query("UPDATE basket_product SET quantity = quantity + 1 WHERE id = :productId")
    suspend fun incrementProductQuantity(productId: Int)

    @Query("UPDATE basket_product SET quantity = quantity - 1 WHERE id = :productId AND quantity > 1")
    suspend fun decrementProductQuantity(productId: Int)

    @Query("SELECT * from basket_product Where quantity > 1")
    fun getBasketProductListItemGreaterThanZero(): LiveData<List<ProductListItem>>
}

