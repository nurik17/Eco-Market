package com.example.ecomarket.data.entity

import com.example.ecomarket.presentation.history.HistoryListType

data class OrderedItem(
    val address: String,
    val comments: String,
    val created_at: String,
    val delivery_cost: Int,
    val order_number: Int,
    val ordered_products: List<OrderedProduct>,
    val phone_number: String,
    val reference_point: String,
    val total_amount: String,
    val viewType: HistoryListType?
)