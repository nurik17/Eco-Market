package com.example.ecomarket.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "basket_product")
@Parcelize
data class ProductListItem(
    @PrimaryKey
    val id: Int,
    val category: Int,
    val description: String,
    val image: String,
    val price: String,
    val quantity: Int = 0,
    val title: String
): Parcelable