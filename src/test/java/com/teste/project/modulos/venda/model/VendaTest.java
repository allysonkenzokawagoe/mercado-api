package com.teste.project.modulos.venda.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VendaTest {

    @Test
    void of_deveCriarVenda_quandoSolicitado() {
        var venda = Venda.of();

        assertThat(venda.getValor()).isEqualTo(0.0);
    }

}
