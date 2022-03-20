package com.base.ecommerce.dto

import java.io.Serializable

data class CategoryDto(
    val id: Long?,

    val categoryName: String?,



    ):Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CategoryDto

        if (id != other.id) return false
        if (categoryName != other.categoryName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (categoryName?.hashCode() ?: 0)
        return result
    }

}
