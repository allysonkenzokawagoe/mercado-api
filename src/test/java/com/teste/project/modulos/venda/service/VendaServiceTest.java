package com.teste.project.modulos.venda.service;

import com.teste.project.modulos.autenticacao.service.AutenticacaoService;
import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.user.service.UsuarioService;
import com.teste.project.modulos.venda.model.Venda;
import com.teste.project.modulos.venda.repository.VendaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.teste.project.modulos.usuario.helper.UsuarioHelper.umUsuario;
import static com.teste.project.modulos.venda.helper.VendaHelper.umaVenda;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VendaServiceTest {

    @Mock
    private VendaRepository repository;

    @Mock
    private AutenticacaoService autenticacaoService;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private VendaService service;

    @Test
    void criarVenda_deveCriarVenda_quandoSolicitado() {
        var venda = Venda.of(umUsuario());

        when(autenticacaoService.getUsuarioId()).thenReturn("1");
        when(usuarioService.getById("1")).thenReturn(umUsuario());

        assertThatCode(() -> service.criarVenda()).doesNotThrowAnyException();

        verify(repository).save(venda);
    }

    @Test
    void getById_deveRetornarVenda_quandoExistente() {
        when(repository.findById(1)).thenReturn(Optional.of(umaVenda()));

        assertThatCode(() -> service.getById(1)).doesNotThrowAnyException();
    }

    @Test
    void getById_deveLancarNotFoundException_quandoNaoEncontrado() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThatCode(() -> service.getById(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Venda não existente");
    }

    @Test
    void save_deveSalvarVenda_quandoSolicitado() {
        when(repository.save(umaVenda())).thenReturn(umaVenda());

        assertThatCode(() -> service.save(umaVenda())).doesNotThrowAnyException();

        verify(repository).save(umaVenda());
    }

}
