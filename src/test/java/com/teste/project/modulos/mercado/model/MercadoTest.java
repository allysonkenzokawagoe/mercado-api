package com.teste.project.modulos.mercado.model;

import org.junit.jupiter.api.Test;

import static com.teste.project.modulos.mercado.helper.MercadoHelper.umMercadoRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class MercadoTest {

    @Test
    void of_deveCriarMercado_quandoSolicitado() {
        var mercado = Mercado.of(umMercadoRequest());

        assertThat(mercado.getNome()).isEqualTo("Mercado 1");
        assertThat(mercado.getCnpj()).isEqualTo("123");
    }
}
