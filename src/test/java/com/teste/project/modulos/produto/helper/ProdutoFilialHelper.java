package com.teste.project.modulos.produto.helper;

import com.teste.project.modulos.produtos.enums.ESituacaoProduto;
import com.teste.project.modulos.produtos.model.ProdutoFilial;
import lombok.experimental.UtilityClass;

import static com.teste.project.modulos.filial.helper.FilialHelper.umaFilial;
import static com.teste.project.modulos.produto.helper.ProdutoHelper.umProduto;

@UtilityClass
public class ProdutoFilialHelper {

    public static ProdutoFilial umProdutoFilial() {
        return new ProdutoFilial(
                null,
                8.99,
                ESituacaoProduto.EM_ESTOQUE,
                umProduto(),
                umaFilial()
        );
    }

    public static ProdutoFilial umProdutoFilialCriado() {
        return new ProdutoFilial(
                null,
                8.99,
                ESituacaoProduto.FORA_ESTOQUE,
                umProduto(),
                umaFilial()
        );
    }

}
