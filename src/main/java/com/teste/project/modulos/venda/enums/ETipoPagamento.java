package com.teste.project.modulos.venda.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ETipoPagamento {

    DINHEIRO("Dinheiro"),
    PIX("Pix"),
    CARTAO_CREDITO("Cartão Crédito"),
    CARTAO_DEBITO("Cartão Débito");

    private final String descricao;

}
