package com.pedidoservice.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RabbitMQConstants {

    @Value("${spring.rabbitmq.queue.name}")
    private String queuProduct;

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeProduct;

    @Value("${spring.rabbitmq.routingKey.name}")
    private String routingKey;


}
