package com.base.ecommerce.dto.request

data class ProductIsActiveUpdateRequest(
    val id: Int,
    val isActive: Boolean
)
