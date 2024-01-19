package com.example.ecomarket.utils

import android.os.Build
import android.os.Bundle
import com.example.ecomarket.domain.ProductListItem

fun Bundle?.getProductItem(key: String): ProductListItem {
    return when {
        Build.VERSION.SDK_INT >= 34 -> this?.getParcelable(key)!!
        else -> @Suppress("DEPRECATION") (this?.getParcelable(key)!!)
    }
}
