package com.base.ecommerce.repository;

import com.base.ecommerce.model.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
