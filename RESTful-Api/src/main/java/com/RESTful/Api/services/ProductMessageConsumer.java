package com.RESTful.Api.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ProductMessageConsumer {

    @RabbitListener(queues = "product-queue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}

