package com.example.ecomarket.domain

data class OrderRequest(
    val address: String,
    val comments: String,
    val phone_number: String,
    val products: List<OrderedProduct>,
    val reference_point: String
)