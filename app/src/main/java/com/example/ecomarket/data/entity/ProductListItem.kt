package com.example.ecomarket.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductListItem(
    val category: Int,
    val description: String,
    val id: Int,
    val image: String,
    val price: String,
    val quantity: Int,
    val title: String
): Parcelable