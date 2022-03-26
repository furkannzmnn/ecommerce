package com.base.ecommerce.service;

import com.base.ecommerce.dto.request.IncrementView;
import com.base.ecommerce.model.Product;
import com.base.ecommerce.model.user.User;
import com.base.ecommerce.repository.ProductRepository;
import com.base.ecommerce.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.logging.Logger;

@Component
public class ProductViewedCountService {

    private static final Logger logger = Logger.getLogger(ProductViewedCountService.class.getName());

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final  KafkaTemplate<String, IncrementView> kafkaTemplate;

    public ProductViewedCountService(ProductRepository productRepository, UserRepository userRepository, KafkaTemplate<String, IncrementView> kafkaTemplate) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }


    public void increaseProductViewedCount(IncrementView incrementView) {
        userRepository.findById(incrementView.getUserId()).ifPresent(user -> functionality(incrementView));
    }


    @KafkaListener(topics = "product-viewed-count", groupId = "group_id", containerFactory = "viewConcurrentKafkaListenerContainerFactory")
    public void notification(@Payload IncrementView incrementView) {
        logger.info("Received notification for product viewed count");
        User user = userRepository.findById(incrementView.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(incrementView.getProductId()).orElse(new Product());
        logger.info("Product viewed count for product " + product.getProductTitle() + " is " + product.getProductName());
        logger.info("User viewed count: " + user.getUsername());

    }

    public void functionality(IncrementView incrementView) {
        Consumer<IncrementView> consumer = s ->  kafkaTemplate.send("product-viewed-count", incrementView);
        consumer.accept(incrementView);
    }
}
