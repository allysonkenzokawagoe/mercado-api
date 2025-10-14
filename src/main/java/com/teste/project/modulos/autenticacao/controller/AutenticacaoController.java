package com.teste.project.modulos.autenticacao.controller;

import com.teste.project.modulos.autenticacao.dto.UsuarioAutenticado;
import com.teste.project.modulos.autenticacao.service.AutenticacaoService;
import com.teste.project.modulos.autenticacao.service.JwtService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("oauth")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @GetMapping
    public UsuarioAutenticado autenticar(@RequestParam @NotBlank String username, @RequestParam @NotBlank String password) throws Exception {
        return autenticacaoService.autenticarUsuario(username, password);
    }

}
