package com.base.ecommerce.service.rate_limiter;

import com.base.ecommerce.exception.GenericException;
import com.base.ecommerce.model.user.User;
import com.base.ecommerce.repository.UserRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RateLimiterService {

    private long hitCount = 0;
    public static final String RATE_LIMITER_KEY = "rate_limiter_key_";

    private final UserRepository userRepository;

    public RateLimiterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Caching(cacheable = {
            @Cacheable(value = RATE_LIMITER_KEY, key = "#userId"),
            @Cacheable(value = RATE_LIMITER_KEY, key = "#hitCount")
    })
    public long getHitCount(Long userId, long hitCount) {
        return hitCount;
    }

    @Caching( put = {
            @CachePut(value = RATE_LIMITER_KEY, key = "#userId"),
            @CachePut(value = RATE_LIMITER_KEY, key = "#hitCount")
    })
    public void incrementHitCount(Long userId, long hitCount) {
        this.hitCount++;
    }


    public void getHttpStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final User user = getUser(authentication.getName());
        if (user != null) {
            if (getHitCount(user.getId(), hitCount) > 10) {
                throw GenericException.builder().message("Rate limit exceeded").status(HttpStatus.TOO_MANY_REQUESTS).build();
            }
            incrementHitCount(user.getId(), hitCount);
        }
    }

    @Cacheable(value = "authentication", key = "#username")
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}


