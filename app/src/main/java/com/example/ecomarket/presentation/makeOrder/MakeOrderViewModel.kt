package com.example.ecomarket.presentation.makeOrder

import androidx.lifecycle.ViewModel
import com.example.ecomarket.data.entity.OrderRequest
import com.example.ecomarket.data.entity.OrderedItem
import com.example.ecomarket.domain.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MakeOrderViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel() {

    suspend fun makeOrder(orderRequest: OrderRequest): OrderedItem = withContext(Dispatchers.IO) {
        repository.createOrder(orderRequest)
    }
}
