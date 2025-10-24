package com.teste.project.modulos.mercado.service;

import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.mercado.model.Mercado;
import com.teste.project.modulos.mercado.repository.MercadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.teste.project.modulos.mercado.helper.MercadoHelper.umMercado;
import static com.teste.project.modulos.mercado.helper.MercadoHelper.umMercadoRequest;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MercadoServiceTest {

    @Mock
    private MercadoRepository repository;

    @InjectMocks
    private MercadoService service;

    @Test
    void cadastrar_deveCadastrarMercado_quandoSolicitao() {
        var mercado = Mercado.of(umMercadoRequest());

        assertThatCode(() -> service.cadastrar(umMercadoRequest())).doesNotThrowAnyException();

        verify(repository).save(mercado);
    }

    @Test
    void getByid_deveRetornarMercado_quandoExistente() {
        when(repository.findById(1)).thenReturn(Optional.of(umMercado()));

        assertThatCode(() -> service.getById(1)).doesNotThrowAnyException();
    }

    @Test
    void getByid_deveLancarValidacaoException_quandoNaoEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThatCode(() -> service.getById(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Mercado não encontrado");
    }
}
