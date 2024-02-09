package com.pedidoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoNotifyer {

    private String nome;
    private String sobrenome;
    private String email;
    private ProdutoRequest produto;

    @Override
    public String toString() {
        return "Cliente: " +
                "nome: " + nome +
                ", sobrenome: " + sobrenome +
                ", email: " + email;
    }
}

