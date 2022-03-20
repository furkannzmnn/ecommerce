package com.base.ecommerce.model

import org.hibernate.Hibernate
import javax.persistence.*

@Entity(name = "favorite_product_like")
data class Favorite @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = 0,

    val productId: Int? = null,

    val userId: Int? = null,


) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Favorite

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
