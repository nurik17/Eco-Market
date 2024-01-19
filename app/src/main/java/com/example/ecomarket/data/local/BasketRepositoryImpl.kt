package com.example.ecomarket.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.ecomarket.data.mapper.ProductListMapper
import com.example.ecomarket.domain.BasketRepository
import com.example.ecomarket.domain.ProductListItem

class BasketRepositoryImpl(private val basketProductDao: BasketProductDao) : BasketRepository {

    private val mapper = ProductListMapper()

    override suspend fun addProducts(products: List<ProductListItem>) {
        basketProductDao.addProducts(mapper.mapListEntityToDbModel(products))
    }

    override fun getAllBasketProduct(): LiveData<List<ProductListItem>> =
        MediatorLiveData<List<ProductListItem>>().apply {
            addSource(basketProductDao.getAllBasketProducts()){
                value = mapper.mapListDbModelToListEntity(it)
            }
    }


   /*  override fun getAllBasketProduct(): LiveData<List<ProductListItem>> = Transformations.map(
            basketProductDao.getAllBasketProduct(){
                mapper.mapListToDbModel(it)
            }
     )*/


    override suspend fun deleteProduct(productId: Int) {
        basketProductDao.deleteProduct(productId)
    }

    override suspend fun deleteAllProducts() {
        basketProductDao.deleteAllProducts()
    }

    override suspend fun incrementProductQuantity(productId: Int) {
        basketProductDao.incrementProductQuantity(productId)
    }

    override suspend fun decrementProductQuantity(productId: Int) {
        basketProductDao.decrementProductQuantity(productId)
    }

    override fun basketItemGreaterThanZero(): LiveData<List<ProductListItem>> =
        MediatorLiveData<List<ProductListItem>>().apply {
            addSource(basketProductDao.getBasketProductListItemGreaterThanZero()){
                value = mapper.mapListDbModelToListEntity(it)
            }
        }
}