package com.example.ecomarket.domain

class OrderedListItemInteraction(private val repository: ProductRepository): OrderedListItemsUseCase {

    override suspend fun executeOrderedListItems(): List<OrderedItem> {
        return repository.getHistoryOrderList()
    }
}