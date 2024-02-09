package com.pedidoservice.event.publsiher;

import com.pedidoservice.dto.ProdutoRequest;

public interface RabbitProductProducer {

    void sendMessageProductProducer(ProdutoRequest request);

}
