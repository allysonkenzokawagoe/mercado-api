package com.teste.project.modulos.filial.service;

import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.endereco.service.EnderecoService;
import com.teste.project.modulos.filiais.model.Filial;
import com.teste.project.modulos.filiais.repository.FilialRepository;
import com.teste.project.modulos.filiais.service.FilialService;
import com.teste.project.modulos.mercado.service.MercadoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.teste.project.modulos.endereco.helper.EnderecoHelper.umEndereco;
import static com.teste.project.modulos.filial.helper.FilialHelper.umaFilial;
import static com.teste.project.modulos.filial.helper.FilialHelper.umaFilialRequest;
import static com.teste.project.modulos.mercado.helper.MercadoHelper.umMercado;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FilialServiceTest {

    @Mock
    private EnderecoService enderecoService;

    @Mock
    private MercadoService mercadoService;

    @Mock
    private FilialRepository repository;

    @InjectMocks
    private FilialService service;

    @Test
    void cadastrarFilial_deveCadastrarFilial_quandoSolicitado() {
        var filial = Filial.of(umEndereco(), "123", umMercado());

        when(mercadoService.getById(1)).thenReturn(umMercado());
        when(enderecoService.salvarEndereco(328, 86600137)).thenReturn(umEndereco());

        assertThatCode(() -> service.salvar(umaFilialRequest(), 1)).doesNotThrowAnyException();

        verify(repository).save(filial);
    }

    @Test
    void getById_deveRetornarFilial_quandoExistir() {
        when(repository.findById(1)).thenReturn(Optional.of(umaFilial()));

        assertThatCode(() -> service.getById(1)).doesNotThrowAnyException();
    }

    @Test
    void getById_deveLancarNotFoundException_quandoNaoEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThatCode(() -> service.getById(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Filial não encontrada");
    }
}
