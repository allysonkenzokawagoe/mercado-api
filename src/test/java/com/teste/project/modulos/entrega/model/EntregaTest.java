package com.teste.project.modulos.entrega.model;

import org.junit.jupiter.api.Test;

import static com.teste.project.modulos.endereco.helper.EnderecoHelper.umEndereco;
import static com.teste.project.modulos.venda.helper.VendaHelper.umaVendaDto;
import static org.assertj.core.api.Assertions.assertThat;

public class EntregaTest {

    @Test
    void of_deveConstruirEntrega_quandoSolicitado() {
        var entrega = Entrega.of(umaVendaDto());

        assertThat(entrega).isNotNull();
        assertThat(entrega.getEndereco()).isEqualTo(umEndereco());
        assertThat(entrega.getClienteNome()).isEqualTo("Allyson");
    }

}
