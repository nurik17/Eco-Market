package com.example.ecomarket.data

import com.example.ecomarket.data.entity.CategoryListItem
import com.example.ecomarket.data.remote.RetrofitClient.api
import javax.inject.Inject

class ProductRepository @Inject constructor() {

    suspend fun getCategoryList(): List<CategoryListItem>{
        return api.getCategoryList()
    }
}

//
//suspend fun getPremieres(month: String, year: String, page: Int?): List<Movie> {
//    return api.getPremieres(month, year, page).items
//}