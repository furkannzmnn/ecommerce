package com.base.ecommerce.dto

import com.base.ecommerce.model.ProductStatus
import java.math.BigDecimal

data class ProductSearchDto(
    val productName: String?,
    val productDesc: String?,
    val productPrice: BigDecimal?,
    val productStatus: ProductStatus?
)
