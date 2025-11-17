package com.teste.project.modulos.cargo.dto;

import com.querydsl.core.types.Predicate;
import com.teste.project.modulos.cargo.predicate.CargoPredicate;
import com.teste.project.modulos.comum.enums.EPermissao;

public record CargoFiltros(
        String nome,
        EPermissao permissao
) {
    public Predicate toPredicate() {
        return new CargoPredicate()
                .comNome(nome)
                .comPermissao(permissao)
                .build();
    }
}
