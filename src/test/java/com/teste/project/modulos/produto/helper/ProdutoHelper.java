package com.teste.project.modulos.produto.helper;

import com.teste.project.modulos.estoque.model.Estoque;
import com.teste.project.modulos.produtos.dto.ProdutoRequest;
import com.teste.project.modulos.produtos.dto.ProdutoRequestEdit;
import com.teste.project.modulos.produtos.enums.ESituacaoProduto;
import com.teste.project.modulos.produtos.enums.ETipoMedida;
import com.teste.project.modulos.produtos.model.Produto;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

import static com.teste.project.modulos.categoria.helper.CategoriaHelper.umaCategoria;
import static com.teste.project.modulos.estoque.helper.EstoqueHelper.umEstoque;
import static com.teste.project.modulos.filial.helper.FilialHelper.umaFilial;

@UtilityClass
public class ProdutoHelper {

    public static Produto umProduto() {
        return new Produto(
                null,
                "Bolacha",
                "Bauduco",
                "Bolacha recheada de chocolate",
                ETipoMedida.QUANTIDADE,
                umaCategoria()
        );
    }

    public static Produto umProdutoEditado() {
        return new Produto(
                null,
                "Refrigerante",
                "Coca-Cola",
                "Refrigerante de cola",
                ETipoMedida.QUANTIDADE,
                umaCategoria()
        );
    }

    public static ProdutoRequest umProdutoRequest() {
        return new ProdutoRequest(
                "Bolacha",
                "Bauduco",
                "Bolacha recheada de chocolate",
                ETipoMedida.QUANTIDADE,
                1
        );
    }

    public static ProdutoRequestEdit umProdutoRequestEdit() {
        return new ProdutoRequestEdit(
                "Refrigerante",
                "Coca-Cola",
                "Refri de coca",
                8.99,
                ETipoMedida.QUANTIDADE,
                1
        );
    }

}
