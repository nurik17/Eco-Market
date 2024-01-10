package com.example.ecomarket.data.remote

import com.example.ecomarket.data.entity.CategoryListItem
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
}
