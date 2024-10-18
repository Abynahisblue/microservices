package com.RESTful.Api.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public ProductMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        System.out.println("Sending message: " + message);
        rabbitTemplate.convertAndSend("product-exchange", "product-routing-key", message);
    }
}

