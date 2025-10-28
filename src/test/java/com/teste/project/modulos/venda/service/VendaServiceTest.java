package com.teste.project.modulos.venda.service;

import com.teste.project.modulos.autenticacao.service.AutenticacaoService;
import com.teste.project.modulos.user.service.UsuarioService;
import com.teste.project.modulos.venda.model.Venda;
import com.teste.project.modulos.venda.repository.VendaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.teste.project.modulos.usuario.helper.UsuarioHelper.umUsuario;
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


}
