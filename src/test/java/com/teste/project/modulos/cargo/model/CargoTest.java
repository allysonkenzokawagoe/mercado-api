package com.teste.project.modulos.cargo.model;

import com.teste.project.modulos.comum.enums.EPermissao;
import org.junit.jupiter.api.Test;

import static com.teste.project.modulos.cargo.helper.CargoHelper.umCargoRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class CargoTest {

    @Test
    void of_deveConstruirUmCargo_quandoSolicitado() {
        var cargo = Cargo.of(umCargoRequest());

        assertThat(cargo.getNome()).isEqualTo("Administrador");
        assertThat(cargo.getPermissao()).isEqualTo(EPermissao.ADMIN);
    }

}
