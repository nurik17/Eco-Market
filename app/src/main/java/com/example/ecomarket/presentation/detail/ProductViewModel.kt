package com.example.ecomarket.presentation.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomarket.data.entity.ProductListItem
import com.example.ecomarket.data.entity.Resource
import com.example.ecomarket.domain.BasketRepository
import com.example.ecomarket.domain.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    private val basketRepo: BasketRepository
) : ViewModel() {

    private val _getProductList =
        MutableStateFlow<Resource<List<ProductListItem>>>(Resource.UnSpecified())
    val getProductList = _getProductList.asStateFlow()

    private var _getAllBasketProducts: LiveData<List<ProductListItem>>? = basketRepo.getAllBasketProduct()
    val getAllBasketProducts = _getAllBasketProducts

    init {
        getProductList()
    }

    fun getAllBasketProducts(){
        _getAllBasketProducts = basketRepo.getAllBasketProduct()
    }


    private fun getProductList() {
        viewModelScope.launch {
            try {
                _getProductList.value = Resource.Loading()
                val result = withContext(Dispatchers.IO) {
                    getProductListUseCase.executeGetProductList()
                }
                _getProductList.value = Resource.Success(result)
            } catch (e: Exception) {
                _getProductList.value =
                    Resource.Error(e.message.toString())
            }
        }
    }
    fun addProductToBasket(product: ProductListItem) {
        viewModelScope.launch {
            basketRepo.addProduct(product)
        }
    }

    fun addProductsToBasket(products: List<ProductListItem>) {
        viewModelScope.launch {
            basketRepo.addProducts(products)
        }
    }

    fun incrementProductQuantity(product: ProductListItem) {
        viewModelScope.launch {
            basketRepo.incrementProductQuantity(product.id)
            Log.d("ProductViewModel", "incrementProductQuantity: ${product.quantity}")
        }
    }

    fun decrementProductQuantity(product: ProductListItem) {
        viewModelScope.launch {
            basketRepo.decrementProductQuantity(product.id)
        }
    }
}