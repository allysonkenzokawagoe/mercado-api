package com.teste.project.modulos.cargo.dto;

import com.teste.project.modulos.comum.enums.EPermissao;

import java.util.List;

public record CargoRequest(
        String nome,
        EPermissao permissao
) {
}
