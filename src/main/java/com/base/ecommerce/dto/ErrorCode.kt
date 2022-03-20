package com.base.ecommerce.dto

enum  class ErrorCode(name: String, ordinal: Int) {
    PRODUCT_NOT_FOUND(name = "product is not found", ordinal = 0),
    PRODUCT_DONT_CREATE(name = "product don't created", ordinal = 0),
    PRODUCT_DONT_UPDATE(name = "product don't updated", ordinal = 0),

    CATEGORY_NOT_FOUND(name = "category is not found", ordinal = 0),
    CATEGORY_DONT_CREATE(name = "category don't created", ordinal = 0),

    OBJECT_DELETED(name = "object deleted", ordinal = 0),

    DISCOUNTED_INVALID_VALUE(name = "DiscountedInvalidException" , ordinal = 0),

    VALIDATION_EXCEPTION(name = "ValidationException" , ordinal = 0);


}