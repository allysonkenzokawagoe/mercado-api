package com.teste.project.modulos.estoque.controller;

import com.teste.project.modulos.comum.dto.PageResponse;
import com.teste.project.modulos.estoque.dto.EstoqueResponse;
import com.teste.project.modulos.estoque.service.EstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/estoque")
public class EstoqueController {

    private final EstoqueService service;

    @GetMapping
    public PageResponse<EstoqueResponse> buscarEstoque() {
        return service.buscarTodos();
    }
}
