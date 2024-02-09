package com.pedidoservice.event.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigConsumer {


    @Value("${spring.rabbitmq.queue[0].name}")
    private String queueName;

    @Value("${spring.rabbitmq.exchange[0].name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routingKey[0].name}")
    private String routingName;


    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding binding(TopicExchange topicExchange, Queue queue) {
        return BindingBuilder.bind(queue())
                .to(topicExchange())
                .with(routingName);
    }

}
