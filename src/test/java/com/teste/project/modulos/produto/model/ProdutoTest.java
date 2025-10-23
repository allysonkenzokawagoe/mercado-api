package com.teste.project.modulos.produto.model;

import com.teste.project.modulos.produtos.enums.ETipoMedida;
import com.teste.project.modulos.produtos.model.Produto;
import org.junit.jupiter.api.Test;

import static com.teste.project.modulos.produto.helper.ProdutoHelper.umProdutoRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class ProdutoTest {

    @Test
    void of_deveCriarUmProduto_quandoSolicitado() {
        var produto = Produto.of(umProdutoRequest());

        assertThat(produto.getNome()).isEqualTo("Bolacha");
        assertThat(produto.getMarca()).isEqualTo("Bauduco");
        assertThat(produto.getDescricao()).isEqualTo("Bolacha recheada de chocolate");
        assertThat(produto.getTipoMedida()).isEqualTo(ETipoMedida.QUANTIDADE);
    }

}
