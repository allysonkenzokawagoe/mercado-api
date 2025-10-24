package com.teste.project.modulos.produto.model;

import com.teste.project.modulos.produtos.model.ProdutoVenda;
import org.junit.jupiter.api.Test;

import static com.teste.project.modulos.produto.helper.ProdutoFilialHelper.umProdutoFilial;
import static com.teste.project.modulos.venda.helper.VendaHelper.umaVenda;
import static org.assertj.core.api.Assertions.assertThat;

public class ProdutoVendaTest {

    @Test
    void of_deveCriarProdutoVenda_quandoSolicitado() {
        var produtoVenda = ProdutoVenda.of(umProdutoFilial(), umaVenda(), 10.0, 89.90);

        assertThat(produtoVenda.getQuantidade()).isEqualTo(10.0);
        assertThat(produtoVenda.getSubTotal()).isEqualTo(89.90);
    }

}
