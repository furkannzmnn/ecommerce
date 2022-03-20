package com.base.ecommerce.repository;

import com.base.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Integer> , JpaSpecificationExecutor<Product> {

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1%"
            + " OR p.productDesc LIKE %?1%"
            + " OR p.productTitle LIKE %?1%"
            + " OR CONCAT(p.productPrice, '') LIKE %?1%")
    Optional<Product> getProductByProductName(String keyword);

    List<Product> getByProductNameOrCategory_Id(String productName , Long categoryId);

    Optional<Product> findById(int id);

}
