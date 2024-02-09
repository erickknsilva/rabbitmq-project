package com.pedidoservice.event.consumer;


import com.pedidoservice.dto.ProdutoRequest;
import com.pedidoservice.event.publish.RabbitMQPublisher;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "${spring.rabbitmq.queue[0].name}")
@Log4j2
public class RabbitMQConsumer {

    private final RabbitMQPublisher rabbitMQPublisher;

    public RabbitMQConsumer(RabbitMQPublisher rabbitMQPublisher) {
        this.rabbitMQPublisher = rabbitMQPublisher;
    }

    @RabbitHandler
    public void consumerProduct(@Payload ProdutoRequest request) {
        log.info(String.format("Product recebido com sucesso %s", request.toString()));
        rabbitMQPublisher.sendJsonProduct(request);

    }
}
