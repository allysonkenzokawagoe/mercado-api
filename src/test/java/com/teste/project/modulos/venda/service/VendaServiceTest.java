package com.teste.project.modulos.venda.service;

import com.teste.project.modulos.venda.model.Venda;
import com.teste.project.modulos.venda.repository.VendaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VendaServiceTest {

    @Mock
    private VendaRepository repository;

    @InjectMocks
    private VendaService service;

    @Test
    void criarVenda_deveCriarVenda_quandoSolicitado() {
        var venda = Venda.of();

        assertThatCode(() -> service.criarVenda()).doesNotThrowAnyException();

        verify(repository).save(venda);
    }


}
