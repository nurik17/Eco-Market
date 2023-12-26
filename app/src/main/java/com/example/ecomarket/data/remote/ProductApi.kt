package com.example.ecomarket.data.remote

import com.example.ecomarket.data.entity.CategoryList
import com.example.ecomarket.utils.Constant.X_CSRFToken
import retrofit2.http.GET
import retrofit2.http.Headers

interface ProductApi {

    @Headers("X-CSRFToken: $X_CSRFToken")
    @GET("product-category-list")
    suspend fun getCategoryList(): CategoryList
}
