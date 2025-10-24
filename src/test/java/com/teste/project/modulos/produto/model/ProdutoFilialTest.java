package com.teste.project.modulos.produto.model;

import com.teste.project.modulos.produtos.enums.ESituacaoProduto;
import com.teste.project.modulos.produtos.model.ProdutoFilial;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProdutoFilialTest {

    @Test
    void of_deveCriarProdutoFilial_quandoSolicitado() {
        var produto = ProdutoFilial.of(8.99);

        assertThat(produto.getPreco()).isEqualTo(8.99);
        assertThat(produto.getSituacao()).isEqualTo(ESituacaoProduto.FORA_ESTOQUE);
    }

}
