package com.base.ecommerce.service;

import com.base.ecommerce.dto.SalesApplicationDto;
import com.base.ecommerce.exception.customException.CustomerNotFoundException;
import com.base.ecommerce.model.ProductStatus;

import java.math.BigDecimal;

public abstract class SalesApplicationTemplate {

    protected final CustomerService customerService;

    protected SalesApplicationTemplate(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void apply(SalesApplicationDto salesApplicationDto) {

        if (salesApplicationDto.getCustomerId() == null) {
            throw new CustomerNotFoundException("customer not found");
        }
        findCustomer(salesApplicationDto);
    }

    public abstract void findCustomer(SalesApplicationDto applicationDto);
    public abstract BigDecimal calculatePay(ProductStatus productStatus);
}
