package com.example.ecomarket.domain

import com.example.ecomarket.data.entity.OrderedItem

class OrderedListItemInteraction(private val repository: ProductRepository): OrderedListItemsUseCase {


    override suspend fun executeOrderedListItems(): List<OrderedItem> {
        return repository.getHistoryOrderList()
    }
}