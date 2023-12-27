package com.example.ecomarket.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryListItem(
    val id: Int,
    val image: String,
    val name: String
) : Parcelable