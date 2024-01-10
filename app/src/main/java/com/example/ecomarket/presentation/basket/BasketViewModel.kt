package com.example.ecomarket.presentation.basket

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomarket.data.entity.ProductListItem
import com.example.ecomarket.domain.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repository: BasketRepository
) : ViewModel() {

    fun getAllBasketItems() = repository.basketItemGreaterThanZero()

    fun incrementProductQuantity(product: ProductListItem) {
        viewModelScope.launch {
            repository.incrementProductQuantity(product.id)
            Log.d("ProductViewModel", "incrementProductQuantity: ${product.quantity}")
        }
    }

    fun decrementProductQuantity(product: ProductListItem) {
        viewModelScope.launch {
            repository.decrementProductQuantity(product.id)
        }
    }

    fun deleteProductById(product: ProductListItem){
        viewModelScope.launch {
            repository.deleteProduct(product.id)
        }
    }

    fun deleteAllProduct(){
        viewModelScope.launch {
            repository.deleteAllProducts()
        }
    }
}