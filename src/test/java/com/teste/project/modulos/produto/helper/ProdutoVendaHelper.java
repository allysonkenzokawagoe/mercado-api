package com.teste.project.modulos.produto.helper;

import com.teste.project.modulos.produtos.model.ProdutoVenda;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

import static com.teste.project.modulos.produto.helper.ProdutoFilialHelper.umProdutoFilial;
import static com.teste.project.modulos.venda.helper.VendaHelper.umaVenda;

@UtilityClass
public class ProdutoVendaHelper {

    public static ProdutoVenda umProdutoVenda() {
        return new ProdutoVenda(
                null,
                10.0,
                89.90,
                LocalDateTime.now().withNano(0),
                umProdutoFilial(),
                umaVenda()
        );
    }

}
