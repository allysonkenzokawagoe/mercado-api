package com.teste.project.modulos.produtos.dto;

public record ProdutoResponse(
        String nome,
        String marca,
        String descricao,
        String categoriaNome
) {
}
