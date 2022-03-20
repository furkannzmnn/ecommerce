package com.base.ecommerce.service;

import com.base.ecommerce.model.user.CustomerPrice;

import java.math.BigDecimal;

import static com.base.ecommerce.dto.CustomerConstant.CORPORATE_COMMISSION;
import static com.base.ecommerce.dto.CustomerConstant.INDIVIDUAL_COMMISSION;

public enum CustomerCommissionFlow {

    INDIVIDUAL_CUSTOMER {
        @Override
        public BigDecimal execute(CustomerPrice customer) {
            return INDIVIDUAL_COMMISSION;
        }
    },

    CORPORATE_CUSTOMER {
        @Override
        public BigDecimal execute(CustomerPrice customerPrice) {
            return CORPORATE_COMMISSION;
        }
    };

    public abstract BigDecimal execute(CustomerPrice customerPrice);
}
