package com.example.ecomarket.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class ProductListItem(
    val id: Int,
    val category: Int,
    val description: String,
    val image: String,
    val price: String,
    val quantity: Int = 0,
    val title: String
): Parcelable