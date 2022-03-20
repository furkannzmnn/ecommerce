package com.base.ecommerce.dto.request

import com.base.ecommerce.model.*
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class ProductRequest constructor(
    val id: Int?,
    @field:NotBlank(message = "product name is not empty")
    val productName: String?,
    @field:NotBlank(message = "product title is not empty")
    val productTitle: String?,
    @field:NotBlank(message = "product description is not empty")
    val productDesc: String?,
    val creatAt: LocalDateTime?,
    val updateAt: LocalDateTime?,
    @NotNull(message = "price is not be empty")
    val productPrice: ProductPrice?,
    val category: Category?,
    var productImageURL: String? = "",
    @NotNull
    val productStatus: ProductStatus?,
    @JsonIgnore
    val favorite: Set<Favorite>?
) {
    data class Builder(
        var id: Int? = null,
        var productName: String? = null,
        var productTitle: String? = null,
        var productDesc: String? = null,
        var creatAt: LocalDateTime? = null,
        var updateAt: LocalDateTime? = null,
        var productPrice: ProductPrice? = null,
        var category: Category? = null,
        var productImageURL: String? = "",
        var productStatus: ProductStatus? = null,
        var favorite: Set<Favorite>? = setOf()
    ){
        fun id(int: Int) = apply { this.id = id }
        fun productName(productName: String) = apply { this.productName = productName }
        fun productTitle(productTitle: String) = apply { this.productTitle = productTitle }
        fun productDesc(productDesc: String) = apply { this.productDesc = productDesc }
        fun productImageURL(productImageURL: String) = apply { this.productImageURL = productImageURL }
        fun creatAt(creatAt: LocalDateTime) = apply { this.creatAt = creatAt }
        fun updateAt(updateAt: LocalDateTime) = apply { this.updateAt = updateAt }
        fun productPrice(productPrice: ProductPrice) = apply { this.productPrice = productPrice }
        fun category (category: Category) = apply { this.category = category }
        fun productStatus (productStatus: ProductStatus) = apply { this.productStatus = productStatus }
        fun favorite (favorite: Favorite) = apply { this.favorite = setOf(favorite) }
        fun build() = ProductRequest(id,productName, productTitle, productDesc, creatAt, updateAt, productPrice, category, productImageURL, productStatus, favorite)
    }
}
