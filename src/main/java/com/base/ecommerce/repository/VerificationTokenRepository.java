package com.base.ecommerce.repository;

import com.base.ecommerce.model.user.User;
import com.base.ecommerce.model.user.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {

    VerificationToken findByToken(String token);
    // find by user
    VerificationToken findByUser(User user);
}
