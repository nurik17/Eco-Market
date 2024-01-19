package com.example.ecomarket.domain

interface OrderedListItemsUseCase {

    suspend fun executeOrderedListItems() : List<OrderedItem>
}