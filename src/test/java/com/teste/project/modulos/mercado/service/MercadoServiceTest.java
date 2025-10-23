package com.teste.project.modulos.mercado.service;

import com.teste.project.modulos.mercado.model.Mercado;
import com.teste.project.modulos.mercado.repository.MercadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.teste.project.modulos.mercado.helper.MercadoHelper.umMercado;
import static com.teste.project.modulos.mercado.helper.MercadoHelper.umMercadoRequest;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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


}
