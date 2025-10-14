package com.teste.project.modulos.user.predicate;

import com.teste.project.modulos.comum.predicate.PredicateBase;

import static com.teste.project.modulos.user.model.QUsuario.usuario;

public class UsuarioPredicate extends PredicateBase {

    public UsuarioPredicate comNomeMercado(String nomeMerdado) {
        if(nomeMerdado != null) {
            builder.and(usuario.filial.mercado.nome.eq(nomeMerdado));
        }
        return this;
    }

    public UsuarioPredicate comEndereco(String cidade, String logradouro, Integer numero) {
        if(cidade != null &&  logradouro != null && numero != null) {
            var cond = usuario.filial.endereco.localidade.eq(cidade)
                    .and(usuario.filial.endereco.logradouro.eq(logradouro))
                    .and(usuario.filial.endereco.numero.eq(numero));

            builder.and(cond);
        }
        return this;
    }

}
