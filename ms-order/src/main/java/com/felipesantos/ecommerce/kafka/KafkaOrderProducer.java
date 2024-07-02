package com.felipesantos.ecommerce.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaOrderProducer {

    @Value("${kafka.topic.order-topic}")
    private String topic;

    private final KafkaTemplate<String, OrderConfirmationRequest> kafkaTemplate;

    public void sendOrderConfirmation(OrderConfirmationRequest orderConfirmationRequest) {
        log.info("Sending order confirmation...");
        Message<OrderConfirmationRequest> message = MessageBuilder
                .withPayload(orderConfirmationRequest)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
        kafkaTemplate.send(message);
    }
}
