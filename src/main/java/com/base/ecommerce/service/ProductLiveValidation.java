package com.base.ecommerce.service;

import com.base.ecommerce.core.utils.StringUtils;
import com.base.ecommerce.dto.ErrorCode;
import com.base.ecommerce.dto.request.ProductRequest;
import com.base.ecommerce.exception.GenericException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class ProductLiveValidation {

    private ProductLiveValidation() {
        throw new IllegalStateException("Utility class");
    }

    public static void isValid(ProductRequest product) {
        Map<String, String> errors = new HashMap<>();
        Map<Integer, String> tuple = new HashMap<>();
        tuple.put(1, product.getProductDesc());
        tuple.put(2, product.getProductName());
        tuple.put(3, product.getProductTitle());
        tuple.put(4, product.getProductImageURL());

        tuple.values().stream()
                .filter(StringUtils::isEmpty)
                .findFirst()
                .ifPresent(error -> errors.put(error, "Required"));


        if (!errors.isEmpty()) {
            throw GenericException.builder().errorCode(ErrorCode.VALIDATION_EXCEPTION).build();
        }
    }
}
