package com.teste.project.modulos.estoque.controller;

import com.teste.project.modulos.estoque.model.Estoque;
import com.teste.project.modulos.estoque.service.EstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/estoque")
public class EstoqueController {

    private final EstoqueService service;

    @PostMapping("{produtoId}")
    public void adicionarAoEstoque(@PathVariable Integer produtoId, @RequestBody Double quantidade) {
        service.adicionarEstoque(produtoId, quantidade);
    }

    @GetMapping
    public List<Estoque> getEstoque() {
        return service.buscarTodos();
    }
}
