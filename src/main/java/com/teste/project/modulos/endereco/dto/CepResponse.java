package com.teste.project.modulos.endereco.dto;

public record CepResponse(
        String cep,
        String localidade,
        String logradouro,
        String uf,
        String estado
){
}
