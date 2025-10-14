package com.teste.project.modulos.produtos.controller;

import com.teste.project.modulos.produtos.dto.ProdutoRequest;
import com.teste.project.modulos.produtos.model.Produto;
import com.teste.project.modulos.produtos.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public void cadastrar(@RequestBody @Valid ProdutoRequest request) {
        service.cadastrar(request);
    }

    @GetMapping
    public Page<Produto> listarTodos() {
        return service.listarTodos();
    }

}
