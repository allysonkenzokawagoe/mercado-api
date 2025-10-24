package com.teste.project.modulos.venda.helper;

import com.teste.project.modulos.produtos.model.ProdutoVenda;
import com.teste.project.modulos.venda.enums.ETipoPagamento;
import com.teste.project.modulos.venda.model.Venda;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.List;

@UtilityClass
public class VendaHelper {

    public static Venda umaVenda() {
        return new Venda(
                null,
                100.0,
                LocalDateTime.now().withNano(0),
                ETipoPagamento.CARTAO_DEBITO,
                List.of(new ProdutoVenda())
        );
    }

}
