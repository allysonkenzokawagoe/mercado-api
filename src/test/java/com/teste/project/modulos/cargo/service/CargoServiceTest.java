package com.teste.project.modulos.cargo.service;

import com.teste.project.modulos.cargo.model.Cargo;
import com.teste.project.modulos.cargo.repository.CargoRepository;
import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.comum.exceptions.ValidacaoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static com.teste.project.modulos.cargo.helper.CargoHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CargoServiceTest {

    @Mock
    private CargoRepository repository;

    @InjectMocks
    private CargoService service;

    @Test
    void cadastrar_deveCadastrarCargo_quandoSolicitado() {
        var request = umCargoRequest();

        when(repository.existsByNome(request.nome())).thenReturn(false);
        when(repository.save(any(Cargo.class))).thenReturn(umCargo());

        assertThatCode(() -> service.cadastrar(request)).doesNotThrowAnyException();

        verify(repository).save(umCargo());
    }

    @Test
    void cadastrar_deveLancarValidacaoException_quandoCargoJaExistente() {
        var request = umCargoRequest();

        when(repository.existsByNome(request.nome())).thenReturn(true);

        assertThatCode(() -> service.cadastrar(request)).isInstanceOf(ValidacaoException.class)
                .hasMessage("Esse cargo já foi cadastrado!");

        verify(repository, never()).save(umCargo());
    }

    @Test
    void listarCargos_deveRetornarTodosCargos_quandoSolicitado() {
       when(repository.findAll(Pageable.ofSize(20))).thenReturn(umCargoPage());

       assertThatCode(() -> service.listarCargos()).doesNotThrowAnyException();
    }

    @Test
    void getById_deveRetornarCargo_quandoCargoExistir() {
        when(repository.findById(1)).thenReturn(Optional.of(umCargo()));

        assertThatCode(() -> service.getById(1)).doesNotThrowAnyException();

        assertThat(service.getById(1)).isEqualTo(umCargo());
    }

    @Test
    void getById_deveNotFoundException_quandoCargoNaoEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThatCode(() -> service.getById(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Cargo não encontrado");
    }
}
