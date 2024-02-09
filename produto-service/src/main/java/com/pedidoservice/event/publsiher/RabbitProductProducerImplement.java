package com.pedidoservice.event.publsiher;

import com.pedidoservice.dto.ProdutoRequest;
import com.pedidoservice.config.RabbitMQConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RabbitProductProducerImplement implements RabbitProductProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQConstants rabbitMQConstants;

    public RabbitProductProducerImplement(RabbitTemplate rabbitTemplate, RabbitMQConstants rabbitMQConstants) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQConstants = rabbitMQConstants;
    }

    @Override
    public void sendMessageProductProducer(ProdutoRequest request) {

        log.info(String.format("Enviando mensagem para -> %s", request.toString()));
        rabbitTemplate.convertAndSend(rabbitMQConstants.getExchangeProduct(), rabbitMQConstants.getRoutingKey(), request);
    }


}
