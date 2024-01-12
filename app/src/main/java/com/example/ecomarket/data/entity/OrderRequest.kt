package com.example.ecomarket.data.entity

data class OrderRequest(
    val address: String,
    val comments: String,
    val phone_number: String,
    val products: List<Product>,
    val reference_point: String
)