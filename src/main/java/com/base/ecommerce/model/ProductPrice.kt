package com.base.ecommerce.model

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.NotNull

@Embeddable
class ProductPrice(
    @field:Column(name = "product_price")
    @field:NotNull
    val price: BigDecimal = BigDecimal.ZERO,

    @field:Column(name = "discounted_price")
    var discountedPrice: BigDecimal? = BigDecimal.ZERO,

    @Enumerated(value = EnumType.STRING)
    val currency: Currency = Currency.TRY,

    @field:Column(name = "service_fee")
    @field:NotNull
    var serviceFee: BigDecimal? = null
) {
    data class Builder constructor(
        var price: BigDecimal = BigDecimal.ZERO,
        var discountedPrice: BigDecimal? = BigDecimal.ZERO,
        var currency: Currency = Currency.TRY,
        var serviceFee: BigDecimal? = null) {

        fun price(price: BigDecimal) = apply { this.price = price}
        fun discountedPrice (discountedPrice: BigDecimal) = apply { this.discountedPrice = discountedPrice }
        fun currency (currency: Currency) = apply { this.currency = currency }
        fun serviceFee (serviceFee: BigDecimal) = apply { this.serviceFee = serviceFee }
        fun build() = ProductPrice(price, discountedPrice, currency, serviceFee)
    }
}