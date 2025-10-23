package com.teste.project.modulos.user.predicate;

import com.teste.project.modulos.comum.predicate.PredicateBase;

import static com.teste.project.modulos.filiais.model.QFilial.filial;
import static com.teste.project.modulos.mercado.model.QMercado.mercado;

public class UsuarioPredicate extends PredicateBase {

    public UsuarioPredicate comNomeMercado(String nomeMercado) {
        if(nomeMercado != null) {
            builder.and(mercado.nome.eq(nomeMercado));
        }
        return this;
    }

    public UsuarioPredicate comEndereco(String cidade, String logradouro, Integer numero) {
        if(cidade != null &&  logradouro != null && numero != null) {
            var cond = filial.endereco.localidade.eq(cidade)
                    .and(filial.endereco.logradouro.eq(logradouro))
                    .and(filial.endereco.numero.eq(numero));

            builder.and(cond);
        }
        return this;
    }

}
