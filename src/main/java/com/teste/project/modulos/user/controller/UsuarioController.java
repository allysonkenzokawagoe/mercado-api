package com.teste.project.modulos.user.controller;

import com.teste.project.modulos.comum.dto.PageResponse;
import com.teste.project.modulos.user.dto.UsuarioRequest;
import com.teste.project.modulos.user.dto.UsuarioFiltro;
import com.teste.project.modulos.user.dto.UsuarioRequestEdit;
import com.teste.project.modulos.user.dto.UsuarioResponse;
import com.teste.project.modulos.user.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("{filialId}")
    public void cadastrar(@Valid @RequestBody UsuarioRequest request, @PathVariable(name = "filialId") Integer filialId) {
        service.registrar(request, filialId);
    }

    @GetMapping("{cargoId}")
    public PageResponse<UsuarioResponse> buscarTodosPorCargo(@PathVariable Integer cargoId) {
        return service.findAllByCargoId(cargoId);
    }

    @GetMapping
    public PageResponse<UsuarioResponse> buscarTodos(UsuarioFiltro filtro) {
        return service.findAll(filtro);
    }

    @PutMapping("{id}")
    public UsuarioResponse editar(@PathVariable String id, @RequestBody UsuarioRequestEdit request) {
        return service.editar(id, request);
    }

    @PutMapping("{id}/inativar")
    public void inativar(@PathVariable String id) {
        service.inativar(id);
    }

}
