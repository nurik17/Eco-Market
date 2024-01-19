package com.example.ecomarket.data.mapper

import com.example.ecomarket.data.entity.ProductListItemDbModel
import com.example.ecomarket.domain.ProductListItem

// data can know about domain but domain can't know about data and other package

class ProductListMapper {

    fun mapEntityToDbModel(productListItem: ProductListItem): ProductListItemDbModel =
        ProductListItemDbModel(
            id = productListItem.id,
            category = productListItem.category,
            description = productListItem.description,
            image = productListItem.image,
            price = productListItem.price,
            quantity = productListItem.quantity,
            title = productListItem.title
        )

    fun mapDbModelToEntity(productItemDbModel: ProductListItemDbModel): ProductListItem =
        ProductListItem(
            id = productItemDbModel.id,  //ctrl+alt+shift + lkm or ctrl+alt+j
            category = productItemDbModel.category,
            description = productItemDbModel.description,
            image = productItemDbModel.image,
            price = productItemDbModel.price,
            quantity = productItemDbModel.quantity,
            title = productItemDbModel.title
        )

    fun mapListDbModelToListEntity(list: List<ProductListItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }

    fun mapListEntityToDbModel(list: List<ProductListItem>) = list.map {
        mapEntityToDbModel(it)
    }
}