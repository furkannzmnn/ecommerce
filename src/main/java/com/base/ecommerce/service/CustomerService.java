package com.base.ecommerce.service;

import com.base.ecommerce.model.user.Customer;
import com.base.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> findByCustomer (int customerId) {
        return customerRepository.findById(customerId);
    }

    @Transactional
    public Customer createCustomer(Customer customer){

        BigDecimal customerCommission = CustomerCommissionFlow
                .valueOf(customer.getCustomerType().name())
                .execute(customer.getCustomerPrice());

        customer.getCustomerPrice().setCommissionFee(customerCommission);
        customerRepository.save(customer);
        return customer;
    }
}
