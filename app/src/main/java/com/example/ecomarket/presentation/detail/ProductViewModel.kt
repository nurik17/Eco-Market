package com.example.ecomarket.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomarket.data.entity.ProductListItem
import com.example.ecomarket.data.entity.Resource
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
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel() {

    private val _getProductList =
        MutableStateFlow<Resource<List<ProductListItem>>>(Resource.UnSpecified())
    val getProductList = _getProductList.asStateFlow()

    init {
        getProductList()
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
}