package com.teste.project.modulos.produtos.dto;

public record ProdutoFilialRequest(
        Double quantidade,
        Double preco,
        String produtoId,
        String filialId
) {
}
