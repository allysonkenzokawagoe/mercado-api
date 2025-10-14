package com.teste.project.modulos.user.service;

import com.teste.project.modulos.autenticacao.service.AutenticacaoService;
import com.teste.project.modulos.cargo.service.CargoService;
import com.teste.project.modulos.comum.dto.PageResponse;
import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.endereco.service.EnderecoService;
import com.teste.project.modulos.filiais.service.FilialService;
import com.teste.project.modulos.user.dto.UserRequest;
import com.teste.project.modulos.user.dto.UsuarioFiltro;
import com.teste.project.modulos.user.dto.UsuarioRequestEdit;
import com.teste.project.modulos.user.dto.UsuarioResponse;
import com.teste.project.modulos.user.enums.ESituacao;
import com.teste.project.modulos.user.mapper.UsuarioMapper;
import com.teste.project.modulos.user.model.Usuario;
import com.teste.project.modulos.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final EnderecoService enderecoService;
    private final CargoService cargoService;
    private final FilialService filialService;
    private final AutenticacaoService autenticacaoService;
    private final UsuarioMapper mapper;

    @Transactional
    public void register(UserRequest request, Integer filialId) {
        validarEmail(request.email());
        var user = Usuario.of(request);
        var cargo = cargoService.getById(request.cargoId());
        var endereco = enderecoService.salvarEndereco(request.enderecoRequest().numero(), request.cepRequest().cep());
        var filial = filialService.getById(filialId);
        user.setSenha(passwordEncoder.encode(request.senha()));
        user.setCargo(cargo);
        user.setEndereco(endereco);
        user.setFilial(filial);
        repository.save(user);
    }

    @Transactional
    public UsuarioResponse editar(String id, UsuarioRequestEdit request) {
        var usuario = getById(id);

        mapper.map(request, usuario);

        repository.save(usuario);

        return mapper.toResponse(usuario);
    }

    @Transactional
    public void inativar(String id) {
        var usuario = getById(id);
        usuario.setSituacao(ESituacao.INATIVO);
        repository.save(usuario);
    }

    public PageResponse<UsuarioResponse> findAllByCargoId(Integer cargoId) {
        var pageRequest = PageRequest.of(0, 20);
        var usuarios = repository.findAllByCargoId(cargoId, pageRequest).map(mapper::toResponse);
        return PageResponse.from(usuarios);
    }

    public PageResponse<UsuarioResponse> findAll(UsuarioFiltro filtro, PageRequest pageRequest) {
        var predicate = filtro.toPredicate(autenticacaoService.getUsuarioAutenticado());
        var usuarios = repository.findAllByPredicate(predicate, pageRequest);
        return PageResponse.from(usuarios);
    }

    public Usuario getById(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Usuáiro não encontrado!"));
    }

    private void validarEmail(String email) {
        if(repository.existsByEmail(email)) {
            throw new NotFoundException("Email existente");
        }
    }
}
