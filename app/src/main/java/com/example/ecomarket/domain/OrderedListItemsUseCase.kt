package com.example.ecomarket.domain

import com.example.ecomarket.data.entity.OrderedItem

interface OrderedListItemsUseCase {

    suspend fun executeOrderedListItems() : List<OrderedItem>
}