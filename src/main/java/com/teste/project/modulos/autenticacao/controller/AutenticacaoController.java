package com.teste.project.modulos.autenticacao.controller;

import com.teste.project.modulos.autenticacao.dto.UsuarioAutenticado;
import com.teste.project.modulos.autenticacao.service.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("oauth")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @PostMapping
    public UsuarioAutenticado autenticar(@RequestParam("username") String username, @RequestParam("password") String password) {
        return autenticacaoService.autenticarUsuario(username, password);
    }
}
