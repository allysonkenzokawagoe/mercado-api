package com.teste.project.modulos.endereco.dto;

public record EnderecoResponse(
        String logradouro,
        Integer numero,
        String localidade,
        String uf,
        String estado,
        String cep
) {
}
