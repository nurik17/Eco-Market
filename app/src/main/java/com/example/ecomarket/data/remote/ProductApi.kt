package com.example.ecomarket.data.remote

import com.example.ecomarket.data.entity.CategoryList
import com.example.ecomarket.data.entity.OrderList
import com.example.ecomarket.data.entity.OrderRequest
import com.example.ecomarket.data.entity.ProductList
import com.example.ecomarket.utils.Constant.X_CSRFToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ProductApi {

    @Headers("X-CSRFToken: $X_CSRFToken")
    @GET("product-category-list")
    suspend fun getCategoryList(): CategoryList

    @Headers("X-CSRFToken: $X_CSRFToken")
    @GET("product-list")
    suspend fun getProductList(): ProductList

    @Headers("X-CSRFToken: $X_CSRFToken")
    @GET("order-list")
    suspend fun getOrderList(): OrderList

    @Headers("X-CSRFToken: $X_CSRFToken")
    @POST("order-create")
    suspend fun sendDataToServer(@Body request: OrderRequest): Call<OrderList>
}
