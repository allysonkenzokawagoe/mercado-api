package com.teste.project.modulos.entrega.service;

import com.teste.project.modulos.entrega.model.Entrega;
import com.teste.project.modulos.entrega.repository.EntregaRepository;
import com.teste.project.modulos.venda.service.VendaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.teste.project.modulos.entrega.helper.EntregaHelper.umaEntrega;
import static com.teste.project.modulos.venda.helper.VendaHelper.umaVenda;
import static com.teste.project.modulos.venda.helper.VendaHelper.umaVendaDto;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EntregaServiceTest {

    @Mock
    private EntregaRepository repository;

    @Mock
    private VendaService vendaService;

    @InjectMocks
    private EntregaService service;

    @Test
    void gerarEntrega_deveGerarEntrega_quandoMensagemConsumida() {
        when(vendaService.getById(1)).thenReturn(umaVenda());

        assertThatCode(() -> service.gerarEntrega(umaVendaDto())).doesNotThrowAnyException();

        var captor = ArgumentCaptor.forClass(Entrega.class);
        verify(repository).save(captor.capture());
    }

    @Test
    void buscarTodos_deveBuscarTodasEntregas_quandoSolicitado() {
        when(repository.findAll()).thenReturn(List.of(umaEntrega()));

        assertThatCode(() -> service.buscarTodos()).doesNotThrowAnyException();
    }

}
