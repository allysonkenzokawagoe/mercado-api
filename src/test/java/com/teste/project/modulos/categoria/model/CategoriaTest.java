package com.teste.project.modulos.categoria.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoriaTest {

    @Test
    void of_deveCriarCategoria_quandoSolicitado() {
        var categoria = Categoria.of("Comida");

        assertThat(categoria.getNome()).isEqualTo("Comida");
    }

}
