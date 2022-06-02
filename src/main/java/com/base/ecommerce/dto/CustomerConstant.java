package com.base.ecommerce.dto;

import java.math.BigDecimal;

public class CustomerConstant {

    private CustomerConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final BigDecimal INDIVIDUAL_COMMISSION = BigDecimal.valueOf(1500);
    public static final BigDecimal CORPORATE_COMMISSION = BigDecimal.valueOf(2000);
}
