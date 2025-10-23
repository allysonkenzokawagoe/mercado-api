package com.teste.project.modulos.filial.model;

import com.teste.project.modulos.filiais.model.Filial;
import org.junit.jupiter.api.Test;

import static com.teste.project.modulos.endereco.helper.EnderecoHelper.umEndereco;
import static com.teste.project.modulos.mercado.helper.MercadoHelper.umMercado;
import static org.assertj.core.api.Assertions.assertThat;

public class FilialTest {

    @Test
    void of_deveCriarFilial_quandoSolicitado() {
        var filial = Filial.of(umEndereco(), "123", umMercado());

        assertThat(filial.getEndereco()).isEqualTo(umEndereco());
        assertThat(filial.getCnpj()).isEqualTo("123");
        assertThat(filial.getMercado()).isEqualTo(umMercado());
    }

}
