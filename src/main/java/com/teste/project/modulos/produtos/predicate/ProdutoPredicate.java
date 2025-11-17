package com.teste.project.modulos.produtos.predicate;

import com.teste.project.modulos.comum.predicate.PredicateBase;
import io.micrometer.common.util.StringUtils;

import static com.teste.project.modulos.produtos.model.QProduto.produto;

public class ProdutoPredicate extends PredicateBase {

    public ProdutoPredicate comNome(String nome) {
        if(StringUtils.isNotEmpty(nome)) {
            builder.and(produto.nome.equalsIgnoreCase(nome));
        }
        return this;
    }

    public ProdutoPredicate comMarca(String marca) {
        if(StringUtils.isNotEmpty(marca)) {
            builder.and(produto.marca.equalsIgnoreCase(marca));
        }
        return this;
    }

    public ProdutoPredicate comDescricao(String descricao) {
        if(StringUtils.isNotEmpty(descricao)) {
            builder.and(produto.descricao.containsIgnoreCase(descricao));
        }
        return this;
    }

    public ProdutoPredicate comCategoriaId(Integer categoriaId) {
        if(categoriaId != null) {
            builder.and(produto.categoria.id.eq(categoriaId));
        }
        return this;
    }

}
