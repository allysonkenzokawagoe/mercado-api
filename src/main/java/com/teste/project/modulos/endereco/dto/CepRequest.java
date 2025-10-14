package com.teste.project.modulos.endereco.dto;

import jakarta.validation.constraints.NotNull;

public record CepRequest(
        @NotNull
        Integer cep
) {
}
