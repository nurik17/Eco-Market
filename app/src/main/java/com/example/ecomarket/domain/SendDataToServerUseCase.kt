package com.example.ecomarket.domain

import com.example.ecomarket.data.entity.OrderRequest
import com.example.ecomarket.data.entity.OrderedItem

interface SendDataToServerUseCase {

    suspend fun executeDataToServer(orderRequest: OrderRequest): List<OrderedItem>
}