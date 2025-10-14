package com.teste.project.modulos.user.controller;

import com.teste.project.modulos.user.dto.UserRequest;
import com.teste.project.modulos.user.dto.UsuarioFiltro;
import com.teste.project.modulos.user.dto.UsuarioResponse;
import com.teste.project.modulos.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/usuario")
public class UserController {

    private final UserService userService;

    @PostMapping("{filialId}")
    public void register(@Valid @RequestBody UserRequest request, @PathVariable(name = "filialId") Integer filialId) {
        userService.register(request, filialId);
    }

    @GetMapping("{cargoId}")
    public List<UsuarioResponse> getAll(@PathVariable Integer cargoId) {
        return userService.findAllByCargoId(cargoId);
    }

    @GetMapping
    public List<UsuarioResponse> getAll(UsuarioFiltro filtro, PageRequest pageRequest) {
        var page = userService.findAll(filtro, pageRequest);
        return page.getContent();
    }

}
