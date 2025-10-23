package com.teste.project.modulos.produtos.controller;

import com.teste.project.modulos.comum.dto.PageResponse;
import com.teste.project.modulos.produtos.dto.ProdutoRequest;
import com.teste.project.modulos.produtos.dto.ProdutoRequestEdit;
import com.teste.project.modulos.produtos.dto.ProdutoResponse;
import com.teste.project.modulos.produtos.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping("cadastrar")
    public void cadastrar(@RequestBody @Valid ProdutoRequest request) {
        service.cadastrar(request);
    }

    @GetMapping
    public PageResponse<ProdutoResponse> listarTodos() {
        return service.listarTodos();
    }

    @PutMapping("{id}")
    public ProdutoResponse atualizar(@PathVariable Integer id, @RequestBody ProdutoRequestEdit request) {
        return service.editar(request, id);
    }

}
