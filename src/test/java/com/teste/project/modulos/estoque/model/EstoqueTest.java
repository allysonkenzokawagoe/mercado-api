package com.teste.project.modulos.estoque.model;

import org.junit.jupiter.api.Test;

import static com.teste.project.modulos.filial.helper.FilialHelper.umaFilial;
import static com.teste.project.modulos.produto.helper.ProdutoFilialHelper.umProdutoFilial;
import static com.teste.project.modulos.produto.helper.ProdutoHelper.umProduto;
import static org.assertj.core.api.Assertions.assertThat;

public class EstoqueTest {

    @Test
    void of_deveCriarEstoque_quandoSolicitado() {
        var estoque = Estoque.of(umProdutoFilial(), umaFilial());

        assertThat(estoque.getQuantidade()).isEqualTo(0.0);
        assertThat(estoque.getFilial()).isEqualTo(umaFilial());
        assertThat(estoque.getProdutoFilial()).isEqualTo(umProdutoFilial());
    }

}
