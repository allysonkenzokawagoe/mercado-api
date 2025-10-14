package com.teste.project.modulos.venda.controller;

import com.teste.project.modulos.produtos.service.ProdutoVendaService;
import com.teste.project.modulos.venda.enums.ETipoPagamento;
import com.teste.project.modulos.venda.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/venda")
public class VendaController {

    private final VendaService service;
    private final ProdutoVendaService produtoVendaService;

    @PostMapping
    public void iniciarVenda() {
        service.criarVenda();
    }

    @PostMapping("{vendaId}/{produtoId}")
    public void cadastrarProdutosVenda(@PathVariable Integer vendaId, @PathVariable Integer produtoId, @RequestBody Double quantidade) {
        produtoVendaService.cadastrarProdutoVenda(vendaId, produtoId, quantidade);
    }

    @PostMapping("{vendaId}/finalizar")
    public void finalizarVenda(@PathVariable Integer vendaId, @RequestBody ETipoPagamento tipoPagamento) {
        produtoVendaService.finalizarVenda(vendaId, tipoPagamento);
    }
}
