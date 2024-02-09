package com.pedidoservice.event.publish;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigPublish {


    @Value("${spring.rabbitmq.queue[1].name}")
    private String queueName;

    @Value("${spring.rabbitmq.exchange[1].name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routingKey[1].name}")
    private String routingName;

    @Bean
    public Queue queuePublisher() {
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange topicExchangePublisher() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding bindingPublisher(TopicExchange topicExchange, Queue queue) {
        return BindingBuilder.bind(queuePublisher())
                .to(topicExchangePublisher())
                .with(routingName);
    }

}
