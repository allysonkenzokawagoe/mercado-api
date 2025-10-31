package com.teste.project.modulos.entrega.helper;

import com.teste.project.modulos.entrega.enums.ESituacaoEntrega;
import com.teste.project.modulos.entrega.model.Entrega;
import lombok.experimental.UtilityClass;

import static com.teste.project.modulos.endereco.helper.EnderecoHelper.umEndereco;
import static com.teste.project.modulos.venda.helper.VendaHelper.umaVenda;

@UtilityClass
public class EntregaHelper {

    public static Entrega umaEntrega() {
        return new Entrega(
                1,
                "Allyson",
                ESituacaoEntrega.ENTREGA_ANDAMENTO,
                umEndereco(),
                umaVenda()
        );
    }

}
