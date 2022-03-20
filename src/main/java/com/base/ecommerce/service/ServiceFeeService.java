package com.base.ecommerce.service;

import com.base.ecommerce.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
public class ServiceFeeService {

    private final ObjectMapper objectMapper;
    private static final Logger logger = LogManager.getLogger(ServiceFeeService.class);

    public ServiceFeeService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "product", groupId = "group_id", containerFactory = "greetingKafkaListenerContainerFactory")
    public void listen(@Payload Product product) {

        ProductTypeFlow.getByProductStatus(Objects.requireNonNull(product.getProductStatus()))
                .typeCheckForServiceFee(product.getProductPrice());
        try {

            String[] strings = objectMapper.writeValueAsString(product).split(",");
            logger.info("Received Product: {}", Arrays.stream(strings).map(s -> s + "\n").reduce("", String::concat));
        } catch (Exception e) {
            logger.error("ServiceFeeService: Exception = {}", e);
        }
    }
}
