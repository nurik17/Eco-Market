package com.example.ecomarket.data.local

import com.example.ecomarket.data.entity.ProductListItem
import com.example.ecomarket.domain.BasketRepository

class BasketRepositoryImpl(private val basketProductDao: BasketProductDao) : BasketRepository {

    override suspend fun addProduct(product: ProductListItem) {
        basketProductDao.addProduct(product)
    }

    override suspend fun addProducts(products: List<ProductListItem>) {
        basketProductDao.addProducts(products)
    }

    override fun getAllBasketProduct() = basketProductDao.getAllBasketProducts()

    override suspend fun deleteProduct(productId: Int) {
        basketProductDao.deleteProduct(productId)
    }

    override suspend fun deleteAllProducts() {
        basketProductDao.deleteAllProducts()
    }

    override suspend fun getBasketProductById(productId: Int): ProductListItem? {
        return basketProductDao.getBasketProductById(productId)
    }

    override suspend fun incrementProductQuantity(productId: Int) {
        basketProductDao.incrementProductQuantity(productId)
    }

    override suspend fun decrementProductQuantity(productId: Int) {
        basketProductDao.decrementProductQuantity(productId)
    }

    override fun basketItemGreaterThanZero() =
        basketProductDao.getBasketProductListItemGreaterThanZero()

}