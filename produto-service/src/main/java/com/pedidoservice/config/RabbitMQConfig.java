package com.pedidoservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Autowired
    private RabbitMQConstants rabbitMQConstants;


    @Bean
    public Queue queueProduct() {
        return new Queue(rabbitMQConstants.getQueuProduct());
    }

    @Bean
    public TopicExchange topicExchangeProduct() {
        return new TopicExchange(rabbitMQConstants.getExchangeProduct());
    }

    @Bean
    public Binding bindingProduct() {
        return BindingBuilder.bind(queueProduct())
                .to(topicExchangeProduct())
                .with(rabbitMQConstants.getRoutingKey());
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        rabbitTemplate.setUseDirectReplyToContainer(false);
        return rabbitTemplate;
    }




}
