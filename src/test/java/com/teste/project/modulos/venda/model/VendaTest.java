package com.teste.project.modulos.venda.model;

import org.junit.jupiter.api.Test;

import static com.teste.project.modulos.usuario.helper.UsuarioHelper.umUsuario;
import static org.assertj.core.api.Assertions.assertThat;

public class VendaTest {

    @Test
    void of_deveCriarVenda_quandoSolicitado() {
        var venda = Venda.of(umUsuario());

        assertThat(venda.getValor()).isEqualTo(0.0);
        assertThat(venda.getUsuario().getNome()).isEqualTo("Allyson");
    }

}
