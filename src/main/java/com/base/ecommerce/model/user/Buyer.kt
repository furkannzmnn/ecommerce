package com.base.ecommerce.model.user

import javax.persistence.*

@Entity
@Table(name = "buyer")
class Buyer constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    val name: String?,
    val lastName: String?,
    val phoneNumber: String?,
    val buyerType: BuyerType = BuyerType.NORMAL,
    val point:Int?

){
    data class Builder constructor(
        var id:Int? = null,
        var name: String? = null,
        var lastName: String? = null,
        var phoneNumber: String? = null,
        var buyerType: BuyerType = BuyerType.NORMAL,
        var point: Int? = null
    ) {

        fun id(id: Int) = apply { this.id = id }
        fun name(name: String?) = apply { this.name = name }
        fun lastName(lastName: String?) = apply { this.lastName = lastName }
        fun phoneNumber(phoneNumber: String?) = apply { this.phoneNumber = phoneNumber }
        fun buyerType(buyerType: BuyerType) = apply { this.buyerType = buyerType }
        fun point(point: Int?) = apply { this.point = point }

        fun build() = Buyer(id,name, lastName, phoneNumber, buyerType,point)

        }
}
