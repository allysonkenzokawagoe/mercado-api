package com.teste.project.modulos.produtos.dto;

import com.teste.project.modulos.produtos.enums.ESituacaoProduto;

public record ProdutoResponse(
        String nome,
        String marca,
        String descricao,
        Double preco,
        String categoriaNome,
        ESituacaoProduto situacao
) {
}
