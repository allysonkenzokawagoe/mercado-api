package com.teste.project.modulos.produtos.controller;

import com.teste.project.modulos.estoque.model.Estoque;
import com.teste.project.modulos.produtos.model.ProdutoFilial;
import com.teste.project.modulos.produtos.service.ProdutoFilialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/produto-filial")
public class ProdutoFilialController {

    private final ProdutoFilialService service;

    @PostMapping("{filialId}/{produtoId}")
    public ProdutoFilial cadastrar(@RequestBody @Valid Double preco, @PathVariable Integer filialId, @PathVariable Integer produtoId) {
        return service.cadastrar(preco, filialId, produtoId);
    }

    @PostMapping("{produtoFilialId}/adicionar-estoque")
    public Estoque adicionarAoEstoque(@PathVariable Integer produtoFilialId, @RequestBody Double quantidade) {
        return service.adicionarAoEstoque(produtoFilialId, quantidade);
    }
}
