package com.base.ecommerce.service.flows;

import com.base.ecommerce.dto.SalesApplicationDto;
import com.base.ecommerce.model.ProductStatus;
import com.base.ecommerce.model.user.Customer;
import com.base.ecommerce.service.CustomerService;
import com.base.ecommerce.service.SalesApplicationTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CorporateCustomerFlow extends SalesApplicationTemplate {
    public CorporateCustomerFlow(CustomerService customerService) {
        super(customerService);
    }

    @Override
    public void findCustomer(SalesApplicationDto applicationDto) {
        Optional<Customer> existsCustomer = customerService.findByCustomer(applicationDto.getCustomerId());

        existsCustomer.ifPresent(customer -> applicationDto.setCustomerId(customer.getId()));
    }

    @Override
    public BigDecimal calculatePay(ProductStatus productStatus) {
        return null;
    }
}
