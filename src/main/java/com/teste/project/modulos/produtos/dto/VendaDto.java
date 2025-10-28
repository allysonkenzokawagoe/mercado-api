package com.teste.project.modulos.produtos.dto;

import com.teste.project.modulos.endereco.model.Endereco;
import com.teste.project.modulos.venda.model.Venda;

public record VendaDto(
        Integer id,
        Endereco endereco,
        String nomeCliente
) {
    public static VendaDto of(Venda venda) {
        return new VendaDto(
                venda.getId(),
                venda.getEndereco(),
                venda.getUsuario().getNome()
        );
    }
}
