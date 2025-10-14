package com.teste.project.modulos.mercado.controller;

import com.teste.project.modulos.mercado.service.MercadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/mercado")
public class MercadoController {

    private final MercadoService service;

    @PostMapping
    public void salvar(@RequestBody String nome) {
        service.salvar(nome);
    }

}
