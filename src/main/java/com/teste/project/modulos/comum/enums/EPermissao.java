package com.teste.project.modulos.comum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EPermissao {

    ADMIN("ADMIN"),
    GERENTE("GERENTE"),
    FUNCIONARIO("FUNCIONARIO");

    public String getRole() {
        return "ROLE_" + name();
    }

    private final String valor;
}
