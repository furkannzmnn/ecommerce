package com.base.ecommerce.model

import java.io.Serializable

enum class ProductStatus:Serializable {
    ZERO,
    NEW,
    MIDDLE,
    USED,
    OLD,
    EXPIRED,
    ACTIVE,
    PASSIVE
}