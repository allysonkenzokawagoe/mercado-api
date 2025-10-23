package com.teste.project.modulos.endereco.model;

import org.junit.jupiter.api.Test;

import static com.teste.project.modulos.endereco.helper.EnderecoHelper.umCepResponse;
import static org.assertj.core.api.Assertions.assertThat;

public class EnderecoTest {

    @Test
    void of_deveCriarEndereco_quandoSolicitado() {
        var endereco = Endereco.of(umCepResponse(), 328);

        assertThat(endereco.getCep()).isEqualTo("86600-137");
        assertThat(endereco.getLocalidade()).isEqualTo("Rolândia");
        assertThat(endereco.getLogradouro()).isEqualTo("Avenida Interventor Manoel Ribas");
        assertThat(endereco.getNumero()).isEqualTo(328);
        assertThat(endereco.getUf()).isEqualTo("PR");
        assertThat(endereco.getEstado()).isEqualTo("Paraná");
    }

}
