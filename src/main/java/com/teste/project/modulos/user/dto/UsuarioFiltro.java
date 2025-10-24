package com.teste.project.modulos.user.dto;

import com.querydsl.core.types.Predicate;
import com.teste.project.modulos.autenticacao.dto.UsuarioAutenticado;
import com.teste.project.modulos.endereco.model.Endereco;
import com.teste.project.modulos.user.predicate.UsuarioPredicate;

public record UsuarioFiltro(
        String nomeMercado,
        Endereco endereco
) {
    public Predicate toPredicate(UsuarioAutenticado usuario) {
        if (!usuario.isAdmin()) return null;

        UsuarioPredicate builder = new UsuarioPredicate();

        if(nomeMercado != null &&  !nomeMercado.trim().isEmpty()) {
            builder.comNomeMercado(nomeMercado);
        }

        if (endereco != null) {
            builder.comEndereco(
                    endereco.getLocalidade(),
                    endereco.getLogradouro(),
                    endereco.getNumero()
            );
        }

        return builder.build();
    }

}
