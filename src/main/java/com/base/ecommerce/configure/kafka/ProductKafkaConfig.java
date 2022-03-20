package com.base.ecommerce.configure.kafka;

import com.base.ecommerce.exception.KafkaErrorHandler;
import com.base.ecommerce.model.Product;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProductKafkaConfig {

    @Value(value = "${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Value(value = "${kafka.group.id}")
    private String groupId;

    @Bean
    public ProducerFactory<String, Product> greetingProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Product> greetingKafkaTemplate() {
        return new KafkaTemplate<>(greetingProducerFactory());
    }

    @Bean
    public ConsumerFactory<String, Product> greetingConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(Product.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Product>
    greetingKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, Product> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(greetingConsumerFactory());
        factory.setErrorHandler(new KafkaErrorHandler());
        return factory;
    }


}
