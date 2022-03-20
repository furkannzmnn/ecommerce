package com.base.ecommerce.exception;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.springframework.kafka.listener.ErrorHandler;

public class KafkaErrorHandler implements ErrorHandler {

    public static final String ERROR_MESSAGE = "Error while processing record: %s";
    public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(KafkaErrorHandler.class);

    @Override
    public void handle(@NotNull Exception e, ConsumerRecord<?, ?> consumerRecord) {
        LOGGER.error(String.format(ERROR_MESSAGE, consumerRecord), e);
    }
}
