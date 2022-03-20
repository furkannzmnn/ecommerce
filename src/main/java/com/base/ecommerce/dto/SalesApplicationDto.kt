package com.base.ecommerce.dto

import com.base.ecommerce.model.CustomerType

data class SalesApplicationDto(
    var salesApplicationId:Int,
    var customerId:Int?,
    var companyName:String,
    val customerType:CustomerType
)
