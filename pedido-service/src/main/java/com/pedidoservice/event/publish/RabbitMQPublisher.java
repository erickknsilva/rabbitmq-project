package com.pedidoservice.event.publish;


import com.pedidoservice.dto.PedidoNotifyer;
import com.pedidoservice.dto.ProdutoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class RabbitMQPublisher {

    @Value("${spring.rabbitmq.exchange[1].name}")
    private String exchange;

    @Value("${spring.rabbitmq.routingKey[1].name}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;


    public void sendJsonProduct(ProdutoRequest request) {


        PedidoNotifyer pedido = PedidoNotifyer.builder().nome("Erick").sobrenome("Silva").email("erickk.nunes100@gmail.com")
                .produto(request).build();

        log.info(String.format("Pedido aceito com sucesso, enviando notificação de aviso para o email %s", pedido.toString()));


        rabbitTemplate.convertAndSend(exchange, routingKey, pedido);
    }


}
