package com.teste.project.modulos.autenticacao.controller;

import com.teste.project.modulos.autenticacao.dto.UsuarioAutenticado;
import com.teste.project.modulos.autenticacao.service.AutenticacaoService;
import com.teste.project.modulos.autenticacao.service.JwtService;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("oauth")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @PostMapping
    public UsuarioAutenticado autenticar(@RequestParam String username, @RequestParam String password) {
        return autenticacaoService.autenticarUsuario(username, password);
    }

    @GetMapping
    public String teste() {
        return "Teste";
    }

}
