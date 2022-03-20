package com.base.ecommerce.model

import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "sales_application")
@Entity
class SalesApplication constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int?,
    val createdDate: LocalDateTime?,
    val customerId:Int?,
    val companyName:String?,
    val salesApplicationType: SalesApplicationType
    ){

    data class Builder constructor(
        var id:Int? = null,
        var createdDate: LocalDateTime? = null,
        var customerId:Int?= null,
        var companyName:String?= null,
        var salesApplicationType: SalesApplicationType = SalesApplicationType.SALES
        ) {

        fun id(id: Int) = apply { this.id = id }
        fun createdDate(createdDate: LocalDateTime) = apply { this.createdDate = createdDate }
        fun customerId(customerId: Int) = apply { this.customerId = customerId }
        fun companyName(companyName: String) = apply { this.companyName = companyName }
        fun salesApplicationType(salesApplicationType: SalesApplicationType) = apply { this.salesApplicationType = salesApplicationType }
        fun build() = SalesApplication(id,createdDate,customerId,companyName,salesApplicationType)
    }
}
