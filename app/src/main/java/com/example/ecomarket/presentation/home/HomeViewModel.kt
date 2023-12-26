package com.example.ecomarket.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomarket.data.ProductRepository
import com.example.ecomarket.data.entity.CategoryListItem
import com.example.ecomarket.data.entity.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val productRepo: ProductRepository) : ViewModel() {


    private val _categoryList =
        MutableStateFlow<Resource<List<CategoryListItem>>>(Resource.UnSpecified())
    val categoryList = _categoryList.asStateFlow()

    init{
        getCategoryList()
    }
    private fun getCategoryList() {
        viewModelScope.launch {
            try {
                _categoryList.value = Resource.Loading()
                val result = productRepo.getCategoryList()
                Log.d("HomeViewModel", "getCategoryList: $categoryList")
                _categoryList.value = Resource.Success(result)
            } catch (e: Exception) {
                _categoryList.value =
                    Resource.Error(e.message ?: "An error occurred in HomeViewModel")
            }
        }
    }
}

/*

private var _premieres = MutableStateFlow<List<Movie>>(emptyList())
val premieres = _premieres.asStateFlow()*/

/*
fun getPremieres() {
    viewModelScope.launch {
        _state.value = State.Loading
        try {
            val premieres = repository.getPremieres(
                currentYear,
                currentMonth,
                null
            ) _premieres . value = premieres
                    Log.d("VIEW_TAG", "getPremieres: $premieres") _state . value = State . Success
        } catch (e: Exception) {
            _state.value = State.Error
        }
    }
}*/
