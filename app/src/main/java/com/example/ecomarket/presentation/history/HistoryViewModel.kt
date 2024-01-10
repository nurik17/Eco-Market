package com.example.ecomarket.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomarket.data.entity.OrderedItem
import com.example.ecomarket.data.entity.Resource
import com.example.ecomarket.domain.OrderedListItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val orderedListItemUseCase: OrderedListItemsUseCase
) : ViewModel(){

    private val _orderHistory = MutableStateFlow<Resource<List<OrderedItem>>>(Resource.UnSpecified())
    val orderHistory = _orderHistory.asStateFlow()

    init {
        getOrderedItemsList()
    }

    private fun getOrderedItemsList(){
        viewModelScope.launch {
            try {
                _orderHistory.value = Resource.Loading()
                val result = withContext(Dispatchers.IO) {
                    orderedListItemUseCase.executeOrderedListItems()
                }
                _orderHistory.value = Resource.Success(result)
            } catch (e: Exception) {
                _orderHistory.value =
                    Resource.Error(e.message.toString())
            }
        }
    }
}