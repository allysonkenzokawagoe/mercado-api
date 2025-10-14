package com.teste.project.modulos.categoria.controller;

import com.teste.project.modulos.categoria.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/categoria")
public class CategoriaController {

    private final CategoriaService service;

    @PostMapping
    public void cadastrar(@RequestBody String nome) {
        service.cadastrar(nome);
    }

}
