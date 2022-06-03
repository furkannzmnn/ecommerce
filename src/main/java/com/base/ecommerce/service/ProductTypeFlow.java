package com.base.ecommerce.service;

import com.base.ecommerce.model.ProductPrice;
import com.base.ecommerce.model.ProductStatus;

import java.math.BigDecimal;

public enum ProductTypeFlow {


    ZERO {
        @Override
        public void typeCheckForServiceFee(ProductPrice price) {
            BigDecimal serviceFee = price.getPrice().multiply(BigDecimal.valueOf(5)).divide(BigDecimal.valueOf(100));
            price.setServiceFee(serviceFee);
        }
    },
    NEW {
        @Override
        public void typeCheckForServiceFee(ProductPrice price) {
            BigDecimal serviceFee = price.getPrice().multiply(BigDecimal.valueOf(4)).divide(BigDecimal.valueOf(100));
            price.setServiceFee(serviceFee);
        }
    },
    MIDDLE {
        @Override
        public void typeCheckForServiceFee(ProductPrice price) {
            BigDecimal serviceFee = price.getPrice().multiply(BigDecimal.valueOf(3)).divide(BigDecimal.valueOf(100));
            price.setServiceFee(serviceFee);
        }
    },
    USED {
        @Override
        public void typeCheckForServiceFee(ProductPrice price) {
            BigDecimal serviceFee = price.getPrice().multiply(BigDecimal.valueOf(2)).divide(BigDecimal.valueOf(100));
            price.setServiceFee(serviceFee);
        }
    },
    OLD {
        @Override
        public void typeCheckForServiceFee(ProductPrice price) {
            BigDecimal serviceFee = price.getPrice().multiply(BigDecimal.valueOf(1)).divide(BigDecimal.valueOf(100));
            price.setServiceFee(serviceFee);
        }
    };

    public abstract void typeCheckForServiceFee(ProductPrice price);

    public static ProductTypeFlow getByProductStatus(ProductStatus productStatus) {
        return ProductTypeFlow.valueOf(productStatus.name());
    }
}
