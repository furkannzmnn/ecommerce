package com.base.ecommerce.model

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "product")
class Product constructor (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    val productName: String?,

    val productTitle: String?,

    val productDesc: String? = "",

    @Embedded
    val productPrice: ProductPrice? = null,

    @CreatedDate
    val creatAt: LocalDateTime? = LocalDateTime.now(),

    val updateAt: LocalDateTime? = LocalDateTime.now(),

    var productImageURL: String? = "",

    var isActive:Boolean? = true,


    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "category_id")
    val category: Category?= null,


    @field:Enumerated(EnumType.STRING)
    var productStatus: ProductStatus? = null,

    val productTimeExpired: LocalDateTime? = null,
    var buyerId: Int? = null,

) {
    data class Builder constructor(
        var id: Int? = null,
        var productName: String? = null,
        var productTitle: String? = null,
        var productDesc: String? = "",
        var productPrice: ProductPrice? = null,
        var creatAt: LocalDateTime? = LocalDateTime.now(),
        var updateAt: LocalDateTime? = LocalDateTime.now(),
        var productImageURL: String? = "",
        var isActive:Boolean? = true,
        var category: Category?= null,
        var productStatus: ProductStatus? = null,
        var productTimeExpired: LocalDateTime? = null,
        var buyerId: Int? = null
    ) {

        fun id(id: Int) = apply { this.id = id }
        fun productName(productName: String) = apply { this.productName = productName }
        fun productTitle(productTitle: String) = apply { this.productTitle = productTitle }
        fun productDesc(productDesc: String) = apply { this.productDesc = productDesc }
        fun productPrice(productPrice: ProductPrice) = apply { this.productPrice = productPrice }
        fun creatAt(creatAt: LocalDateTime) = apply { this.creatAt = creatAt }
        fun updateAt(updateAt: LocalDateTime) = apply { this.updateAt = updateAt }
        fun isActive(isActive: Boolean) = apply { this.isActive = isActive }
        fun category(category: Category) = apply { this.category = category }
        fun buyerId(buyerId: Int) = apply { this.buyerId = buyerId }
        fun productStatus(productStatus: ProductStatus) = apply { this.productStatus = productStatus }
        fun productTimeExpired(productTimeExpired: LocalDateTime) = apply { this.productTimeExpired = productTimeExpired }
        fun build() = Product(id,productName, productTitle, productDesc, productPrice, creatAt, updateAt, productImageURL,
            isActive, category, productStatus,  productTimeExpired, buyerId)
    }
}