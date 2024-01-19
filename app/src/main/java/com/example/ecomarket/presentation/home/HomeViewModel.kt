package com.example.ecomarket.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomarket.domain.CategoryListItem
import com.example.ecomarket.data.entity.Resource
import com.example.ecomarket.domain.CategoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryListUseCase: CategoryListUseCase
) : ViewModel() {

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
                val result = withContext(Dispatchers.IO) {
                    categoryListUseCase.executeCategoryList()
                }
                _categoryList.value = Resource.Success(result)
            } catch (e: Exception) {
                _categoryList.value =
                    Resource.Error(e.message.toString())
            }
        }
    }
}
