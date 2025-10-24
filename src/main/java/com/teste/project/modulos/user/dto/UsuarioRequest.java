package com.teste.project.modulos.user.dto;

import com.teste.project.modulos.endereco.dto.CepRequest;
import com.teste.project.modulos.endereco.dto.EnderecoRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotNull
        Integer cpf,
        @NotNull
        Integer cargoId,
        @NotNull
        Double salario,
        @NotNull
        CepRequest cepRequest,
        @NotNull
        EnderecoRequest enderecoRequest
) {
}
