package com.teste.project.modulos.categoria.service;

import com.teste.project.modulos.categoria.model.Categoria;
import com.teste.project.modulos.categoria.repository.CategoriaRepository;
import com.teste.project.modulos.comum.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository repository;

    @InjectMocks
    private CategoriaService service;

    @Test
    void cadastrar_deveCadastrar_quandoSolicitado() {
        var categoria = Categoria.of("Comida");

        assertThatCode(() -> service.cadastrar("Comida")).doesNotThrowAnyException();

        verify(repository).save(categoria);
    }

    @Test
    void getByid_deveRetornarCategoria_quandoExistente() {
        var categoria =  Categoria.of("Comida");

        when(repository.findById(1)).thenReturn(Optional.of(categoria));

        assertThatCode(() -> service.getById(1)).doesNotThrowAnyException();
    }

    @Test
    void getById_deveLancarNotFoundException_quandoNaoEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThatCode(() -> service.getById(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Categoria não encontrada");
    }
}
