package com.teste.project.modulos.user.dto;

import com.teste.project.modulos.cargo.model.Cargo;

public record UsuarioRequestEdit(
        String nome,
        String email,
        String senha,
        Integer cpf,
        Double salario,
        Cargo cargo
) {
}
