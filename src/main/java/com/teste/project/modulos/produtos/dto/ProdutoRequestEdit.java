package com.teste.project.modulos.produtos.dto;

import com.teste.project.modulos.produtos.enums.ETipoMedida;

public record ProdutoRequestEdit(
        String nome,
        String marca,
        String descricao,
        Double preco,
        ETipoMedida tipoMedida,
        Integer categoriaId
) {
}
