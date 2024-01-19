package com.example.ecomarket.data.entity

sealed interface Response<in T>{
    data class Success<T>(val data: T): Response<T>
    data class Error(val exception: Exception): Response<Any>
}
