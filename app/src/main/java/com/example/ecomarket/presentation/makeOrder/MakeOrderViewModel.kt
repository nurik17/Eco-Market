package com.example.ecomarket.presentation.makeOrder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomarket.data.entity.OrderRequest
import com.example.ecomarket.data.entity.OrderedItem
import com.example.ecomarket.data.entity.Resource
import com.example.ecomarket.domain.SendDataToServerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MakeOrderViewModel @Inject constructor(
): ViewModel(){

    private val _orderResult = MutableStateFlow<Resource<List<OrderedItem>>>(Resource.UnSpecified())
    val orderResult = _orderResult.asStateFlow()


    /*fun makeOrder(orderRequest: OrderRequest){
        viewModelScope.launch {
            try{
                val result = sendDataToServerUseCase.executeDataToServer(orderRequest)
                _orderResult.value = Resource.Success(result)
            }catch (e: Exception){
                _orderResult.value = Resource.Error(e.message.toString())
            }
        }
    }*/

}