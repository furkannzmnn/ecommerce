package com.base.ecommerce.model.user

import com.base.ecommerce.model.CustomerType
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "customer")
 class Customer(
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    val id : Int? = 1,
    @NotNull val firstName: String?="",
    val lastName: String?="",
    val updateAt: LocalDateTime? = LocalDateTime.now(),
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    val customerType: CustomerType = CustomerType.INDIVIDUAL_CUSTOMER,
    @Embedded
    val customerPrice: CustomerPrice ? = null

) {
     data class Builder(
        var id: Int? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var updateAt: LocalDateTime? = null,
        var createdAt: LocalDateTime? = null,
        var customerType: CustomerType = CustomerType.INDIVIDUAL_CUSTOMER,
        var customerPrice: CustomerPrice? = null
     ){
         fun id(id: Int?) = apply { this.id = id }
         fun firstName(firstName: String?) = apply { this.firstName = firstName }
         fun lastName(lastName: String?) = apply { this.lastName = lastName }
         fun updateAt(updateAt: LocalDateTime?) = apply { this.updateAt = updateAt }
         fun createdAt(createdAt: LocalDateTime?) = apply { this.createdAt = createdAt }
         fun customerType(customerType: CustomerType) = apply { this.customerType = customerType }
         fun customerPrice(customerPrice: CustomerPrice?) = apply { this.customerPrice = customerPrice }
         fun build() = Customer(id, firstName, lastName, updateAt, createdAt, customerType, customerPrice)
     }

 }
