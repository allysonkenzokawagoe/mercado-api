package com.teste.project.modulos.cargo.predicate;

import com.teste.project.modulos.comum.enums.EPermissao;
import com.teste.project.modulos.comum.predicate.PredicateBase;
import io.micrometer.common.util.StringUtils;

import static com.teste.project.modulos.cargo.model.QCargo.cargo;

public class CargoPredicate extends PredicateBase {

    public CargoPredicate comNome(String nome) {
        if(StringUtils.isNotEmpty(nome)) {
            builder.and(cargo.nome.equalsIgnoreCase(nome));
        }
        return this;
    }

    public CargoPredicate comPermissao(EPermissao permissao) {
        if(permissao != null) {
            builder.and(cargo.permissao.eq(permissao));
        }
        return this;
    }
}
