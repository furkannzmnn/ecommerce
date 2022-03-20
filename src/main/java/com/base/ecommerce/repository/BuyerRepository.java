package com.base.ecommerce.repository;

import com.base.ecommerce.model.user.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
}
