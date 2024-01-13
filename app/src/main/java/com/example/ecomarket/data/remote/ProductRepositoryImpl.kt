package com.example.ecomarket.data.remote

import android.util.Log
import com.example.ecomarket.data.entity.CategoryListItem
import com.example.ecomarket.data.entity.OrderRequest
import com.example.ecomarket.data.entity.OrderedItem
import com.example.ecomarket.data.entity.ProductListItem
import com.example.ecomarket.domain.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi
) : ProductRepository {
    override suspend fun getCategoryList(): List<CategoryListItem> {
        return api.getCategoryList()
    }

    override suspend fun getProductList(): List<ProductListItem> {
        return api.getProductList()
    }

    override suspend fun getHistoryOrderList(): List<OrderedItem> {
        return api.getOrderList()
    }

    override suspend fun createOrder(orderRequest: OrderRequest): OrderedItem {
        val response = api.createOrder(orderRequest).execute()
        if (response.isSuccessful) {
            Log.d("Repo", "success")
            return response.body() ?: throw NullPointerException("Response body is null")
        } else {
            Log.d("Repo", "error")
            throw Exception("Error creating order")
        }
    }
}
