package com.teste.project.modulos.user.dto;

import com.teste.project.modulos.cargo.dto.CargoResponse;
import com.teste.project.modulos.endereco.dto.EnderecoResponse;

import java.time.LocalDate;

public record UsuarioResponse(
        String id,
        String nome,
        String email,
        LocalDate dataNascimento,
        Integer cpf,
        EnderecoResponse endereco,
        CargoResponse cargo
) {
}
