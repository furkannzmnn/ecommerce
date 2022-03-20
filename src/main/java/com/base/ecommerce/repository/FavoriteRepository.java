package com.base.ecommerce.repository;

import com.base.ecommerce.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {

    Optional<Favorite> findByUserIdAndProductId(int userId, int productId);

}
