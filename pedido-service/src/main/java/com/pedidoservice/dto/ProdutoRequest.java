package com.pedidoservice.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
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