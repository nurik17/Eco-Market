package com.example.ecomarket.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderedProduct(
    val product: Int,
    val quantity: Int
): Parcelable