package com.teste.project.modulos.produtos.dto;

import com.teste.project.modulos.produtos.enums.ETipoMedida;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequest(
        @NotBlank
        String nome,
        @NotBlank
        String marca,
        @NotBlank
        String descricao,
        @NotNull
        Double preco,
        @NotNull
        ETipoMedida tipoMedida,
        @NotNull
        Integer categoriaId
) {
}
