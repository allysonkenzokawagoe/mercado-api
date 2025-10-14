package com.teste.project.modulos.autenticacao.controller;

import com.teste.project.modulos.autenticacao.dto.UsuarioAutenticado;
import com.teste.project.modulos.autenticacao.service.AutenticacaoService;
import com.teste.project.modulos.autenticacao.service.JwtService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("oauth")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @PostMapping
    public UsuarioAutenticado autenticar(@RequestParam @NotBlank String username, @RequestParam @NotBlank String password) throws Exception {
        return autenticacaoService.autenticarUsuario(username, password);
    }

}
