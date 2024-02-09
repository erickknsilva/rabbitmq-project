package com.pedidoservice.controller;

import com.pedidoservice.dto.ProdutoRequest;
import com.pedidoservice.event.publsiher.RabbitProductProducerImplement;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Log4j2
public class ProdutoController {

    private final RabbitProductProducerImplement rabbitProductProducerImplement;

    public ProdutoController(RabbitProductProducerImplement rabbitProductProducerImplement) {
        this.rabbitProductProducerImplement = rabbitProductProducerImplement;
    }

    @PostMapping
    public ResponseEntity<String> savePedido(@RequestBody ProdutoRequest request) {
        // implementar o rabbitmq para enviar no Broker
        rabbitProductProducerImplement.sendMessageProductProducer(request);
        return ResponseEntity.ok("Enviando mensagem para o RabbitMQ...");
    }
}
