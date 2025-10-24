package com.teste.project.modulos.estoque.helper;

import com.teste.project.modulos.estoque.model.Estoque;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

import static com.teste.project.modulos.filial.helper.FilialHelper.umaFilial;
import static com.teste.project.modulos.produto.helper.ProdutoFilialHelper.umProdutoFilial;
import static com.teste.project.modulos.produto.helper.ProdutoFilialHelper.umProdutoFilialCriado;

@UtilityClass
public class EstoqueHelper {

    public static Estoque umEstoque() {
        return new Estoque(
                1,
                10.0,
                LocalDateTime.now().withNano(0),
                umaFilial(),
                umProdutoFilial()
        );
    }

    public static Estoque umEstoqueVazio() {
        return new Estoque(
                1,
                0.0,
                LocalDateTime.now().withNano(0),
                umaFilial(),
                umProdutoFilialCriado()
        );
    }
}
