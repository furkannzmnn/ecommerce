package com.base.ecommerce.service;

import com.base.ecommerce.dto.request.IncrementView;
import com.base.ecommerce.model.user.User;
import com.base.ecommerce.repository.ProductRepository;
import com.base.ecommerce.repository.UserRepository;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.function.Consumer;
import java.util.logging.Logger;

@Component
public class ProductViewedCountService {

    private static final Logger logger = Logger.getLogger(ProductViewedCountService.class.getName());
    private final UserRepository userRepository;
    private final  KafkaTemplate<String, IncrementView> kafkaTemplate;

    public ProductViewedCountService(UserRepository userRepository, KafkaTemplate<String, IncrementView> kafkaTemplate) {
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

        logger.info("User viewed count: " + user.getUsername());

    }

    public void functionality(IncrementView incrementView) {

        ListenableFuture<SendResult<String, IncrementView>> future = kafkaTemplate.send("product-viewed-count", incrementView);

        Consumer<IncrementView> consumer = s -> future.addCallback(new KafkaSendCallback<>() {
            @Override
            public void onSuccess(SendResult<String, IncrementView> result) {
                logger.info("Message sent successfully");
            }

            @Override
            public void onFailure(final KafkaProducerException e) {
                ProducerRecord<String, IncrementView> record = e.getFailedProducerRecord();
                logger.info("Failed to send message: " + record.value());

            }
        });
        consumer.accept(incrementView);
    }
}
