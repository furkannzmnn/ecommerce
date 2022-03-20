package com.base.ecommerce.model.user

import com.base.ecommerce.model.Currency
import java.math.BigDecimal
import javax.persistence.*
import javax.validation.constraints.NotNull

@Embeddable
data class CustomerPrice(

    @Enumerated(value = EnumType.STRING)
    val currency: Currency = Currency.TRY,

    @field:Column(name = "commission_fee")
    @field:NotNull
    var commissionFee: BigDecimal? = BigDecimal.ZERO
)
