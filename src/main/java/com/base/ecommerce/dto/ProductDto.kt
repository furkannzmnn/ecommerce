package com.base.ecommerce.dto

import com.base.ecommerce.model.ProductPrice
import com.base.ecommerce.model.ProductStatus

data class ProductDto(
    val id: Int? ,
    val productName: String? ,

    val productTitle: String? ,

    val productDesc: String? ,

    val productPrice: ProductPrice?,

    val productStatus: ProductStatus?,

    val categoryName: String?,


)

