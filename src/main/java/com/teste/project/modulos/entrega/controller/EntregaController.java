package com.teste.project.modulos.entrega.controller;

import com.teste.project.modulos.entrega.model.Entrega;
import com.teste.project.modulos.entrega.service.EntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/entrega")
public class EntregaController {

    private final EntregaService service;

    @GetMapping
    public List<Entrega> buscarTodos() {
        return service.buscarTodos();
    }
}
