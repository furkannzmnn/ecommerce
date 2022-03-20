package com.base.ecommerce.dto.request

data class UserSaveRequest(
    val name: String,
    val userName: String,
    val email: String,
    val password: String
)
