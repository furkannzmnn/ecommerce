package com.base.ecommerce.configure.redis;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.base.ecommerce.service.rate_limiter.RateLimiterService.RATE_LIMITER_KEY;

@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {



    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .cacheDefaults(cacheConfiguration())
                .withCacheConfiguration(RATE_LIMITER_KEY,
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1)));
    }

    @Bean
    public CacheManager cacheManager(
            RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration defaultCacheConfig =
                RedisCacheConfiguration.defaultCacheConfig();
        defaultCacheConfig.disableCachingNullValues();
        Map<String, RedisCacheConfiguration> cacheConfigurations =
                new HashMap<>();

        cacheConfigurations.put(
                RATE_LIMITER_KEY, defaultCacheConfig.
                        entryTtl(Duration.ofHours(1))
        );
        return RedisCacheManager.builder(connectionFactory).
                cacheDefaults(defaultCacheConfig).
                withInitialCacheConfigurations(cacheConfigurations).
                build();
    }


}
