package com.teste.project.modulos.produtos.dto;

import com.querydsl.core.types.Predicate;
import com.teste.project.modulos.produtos.predicate.ProdutoPredicate;

public record ProdutoFiltro(
        String nome,
        String marca,
        String descricao,
        Integer categoriaId
) {
    public Predicate toPredicate() {
        return new ProdutoPredicate()
                .comNome(nome)
                .comMarca(marca)
                .comDescricao(descricao)
                .comCategoriaId(categoriaId)
                .build();
    }
}
