package com.base.ecommerce.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "category")
class Category(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 1,

    val categoryName: String? = "",

    @JsonIgnore
    @OneToMany(mappedBy = "category" , fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val product: List<Product>? = Collections.emptyList()

) {
    data class Builder(
        var id: Long? = null,
        var categoryName: String? = "",
        var product: List<Product>? = Collections.emptyList()
    ) {

        fun id (id: Long) = apply { this.id = id }
        fun categoryName (categoryName: String) = apply { this.categoryName = categoryName }
        fun product (product: Product) = apply { this.product = listOf(product) }
        fun build() = Category(id, categoryName, product)
    }
}
