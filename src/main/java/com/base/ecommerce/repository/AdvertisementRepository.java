package com.base.ecommerce.repository;

import com.base.ecommerce.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends ProductRepository {

    List<Product> getByIsActiveTrue();

    List<Product> getByIsActiveFalse();

}

