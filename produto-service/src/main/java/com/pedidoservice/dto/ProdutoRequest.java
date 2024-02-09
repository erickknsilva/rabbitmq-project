package com.pedidoservice.dto;

import java.math.BigDecimal;

public record ProdutoRequest(
        String id,
        String nome,
        String marca,
        BigDecimal preco
) {

    @Override
    public String toString() {
        return "Produto " +
                "id: " + id +
                ", nome: " + nome +
                ", marca: " + marca +
                ", preco: " + preco;
    }
}
