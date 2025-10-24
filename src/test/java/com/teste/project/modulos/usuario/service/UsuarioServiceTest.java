package com.teste.project.modulos.usuario.service;

import com.teste.project.modulos.autenticacao.service.AutenticacaoService;
import com.teste.project.modulos.cargo.service.CargoService;
import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.comum.exceptions.ValidacaoException;
import com.teste.project.modulos.endereco.service.EnderecoService;
import com.teste.project.modulos.filiais.service.FilialService;
import com.teste.project.modulos.user.dto.UsuarioFiltro;
import com.teste.project.modulos.user.dto.UsuarioRequestEdit;
import com.teste.project.modulos.user.enums.ESituacao;
import com.teste.project.modulos.user.mapper.UsuarioMapper;
import com.teste.project.modulos.user.model.Usuario;
import com.teste.project.modulos.user.repository.UsuarioRepository;
import com.teste.project.modulos.user.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static com.teste.project.modulos.autenticacao.helper.AutenticacaoHelper.umUsuarioAutenticado;
import static com.teste.project.modulos.cargo.helper.CargoHelper.umCargo;
import static com.teste.project.modulos.endereco.helper.EnderecoHelper.umEndereco;
import static com.teste.project.modulos.filial.helper.FilialHelper.umaFilial;
import static com.teste.project.modulos.usuario.helper.UsuarioHelper.*;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private UsuarioFiltro usuarioFiltro;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EnderecoService enderecoService;

    @Mock
    private CargoService cargoService;

    @Mock
    private FilialService filialService;

    @Mock
    private AutenticacaoService autenticacaoService;

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @Test
    void registrar_deveRegistrarUsuario_quandoSolicitado() {
        when(repository.existsByEmail("allyson@teste.com")).thenReturn(false);
        when(filialService.getById(1)).thenReturn(umaFilial());
        when(cargoService.getById(1)).thenReturn(umCargo());
        when(enderecoService.salvarEndereco(328, 86600137)).thenReturn(umEndereco());
        when(passwordEncoder.encode("123")).thenReturn("123");

        assertThatCode(() -> service.registrar(umUsuarioRequest(), 1)).doesNotThrowAnyException();

        var captor = ArgumentCaptor.forClass(Usuario.class);

        verify(repository).existsByEmail("allyson@teste.com");
        verify(repository).save(captor.capture());
    }

    @Test
    void registrar_deveLancarValidacaoException_quandoEmailExistente() {
        when(repository.existsByEmail("allyson@teste.com")).thenReturn(true);

        assertThatCode(() -> service.registrar(umUsuarioRequest(), 1))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("Email existente");

        verify(repository, never()).save(any(Usuario.class));
    }

    @Test
    void editar_deveEditarUsuario_quandoSolicitado() {
        when(repository.findById("1")).thenReturn(Optional.of(umUsuario()));
        when(usuarioMapper.toResponse(any(Usuario.class))).thenReturn(umUsuarioResponse());

        assertThatCode(() -> service.editar("1", umUsuarioRequestEdit())).doesNotThrowAnyException();

        var captor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioMapper).map(any(UsuarioRequestEdit.class), captor.capture());
        verify(repository).save(captor.capture());
    }

    @Test
    void inativar_deveInativarUsuario_quandoSolicitado() {
        when(repository.findById("1")).thenReturn(Optional.of(umUsuario()));

        assertThatCode(() -> service.inativar("1")).doesNotThrowAnyException();

        var captor = ArgumentCaptor.forClass(Usuario.class);
        verify(repository).save(captor.capture());

        var salvo  = captor.getValue();
        assertEquals(ESituacao.INATIVO, salvo.getSituacao());
    }

    @Test
    void ativar_deveAtivarUsuario_quandoSolicitado() {
        when(repository.findById("1")).thenReturn(Optional.of(umUsuario()));

        assertThatCode(() -> service.ativar("1")).doesNotThrowAnyException();

        var captor = ArgumentCaptor.forClass(Usuario.class);
        verify(repository).save(captor.capture());

        var salvo  = captor.getValue();
        assertEquals(ESituacao.ATIVO, salvo.getSituacao());
    }

    @Test
    void findAllByCargoId_deveRetornarTodosUsuarioPorCargo_quandoSolicitado() {
        when(repository.findAllByCargoId(1, PageRequest.of(0, 20))).thenReturn(new PageImpl<>(List.of(umUsuario())));
        when(usuarioMapper.toResponse(any(Usuario.class))).thenReturn(umUsuarioResponse());

        assertThatCode(() -> service.findAllByCargoId(1)).doesNotThrowAnyException();
    }

    @Test
    void findAll_deveRetornarTodosUsuario_quandoSolicitado() {
        when(autenticacaoService.getUsuarioAutenticado()).thenReturn(umUsuarioAutenticado());
        when(repository.findAllByPredicate(umUsuarioPredicate(), PageRequest.of(0, 20))).thenReturn(new PageImpl<>(List.of(umUsuarioResponse())));

        assertThatCode(() -> service.findAll(umUsuarioFiltro())).doesNotThrowAnyException();
    }

    @Test
    void getById_deveRetornarUsuario_quandoUsuarioExistente() {
        when(repository.findById("1")).thenReturn(Optional.of(umUsuario()));

        assertThatCode(() -> service.getById("1")).doesNotThrowAnyException();
    }

    @Test
    void getById_deveLancarNotFoundException_quandoUsuarioNaoEncontrado() {
        when(repository.findById("1")).thenReturn(Optional.empty());

        assertThatCode(() -> service.getById("1"))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Usuário não encontrado!");
    }
}
