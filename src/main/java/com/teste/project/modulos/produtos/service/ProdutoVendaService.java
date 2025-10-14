package com.teste.project.modulos.produtos.service;

import com.teste.project.modulos.estoque.service.EstoqueService;
import com.teste.project.modulos.produtos.model.ProdutoVenda;
import com.teste.project.modulos.produtos.repository.ProdutoVendaRepository;
import com.teste.project.modulos.venda.enums.ETipoPagamento;
import com.teste.project.modulos.venda.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProdutoVendaService {

    private final ProdutoVendaRepository repository;
    private final ProdutoService produtoService;
    private final VendaService vendaService;
    private final EstoqueService estoqueService;

    @Transactional
    public void cadastrarProdutoVenda(Integer vendaId, Integer produtoId, Double quantidade) {
        var venda = vendaService.getById(vendaId);
        var produto = produtoService.getById(produtoId);
        var valor = quantidade * produto.getPreco();
        var produtoVenda = ProdutoVenda.of(produto, venda, quantidade, valor);
        var estoque = estoqueService.getByProdutoId(produtoId);

        estoque.setQuantidade(estoque.getQuantidade() - quantidade);

        estoqueService.salvar(estoque);
        repository.save(produtoVenda);
    }

    @Transactional
    public void finalizarVenda(Integer vendaId, ETipoPagamento tipoPagamento) {
        var produtosVenda = getProdutosVendidos(vendaId);
        var venda = vendaService.getById(vendaId);

        produtosVenda.forEach(prod -> venda.setValor(venda.getValor() + prod.getSubTotal()));

        venda.setTipoPagamento(tipoPagamento);
        venda.setDataVenda(LocalDateTime.now());
        venda.setProdutoVendidos(produtosVenda);

        vendaService.processarPagamento();
        vendaService.save(venda);
    }

    public List<ProdutoVenda> getProdutosVendidos(Integer vendaId) {
        return repository.findAllByVendaId(vendaId);
    }

    public ProdutoVenda save(ProdutoVenda produtoVenda) {
        return repository.save(produtoVenda);
    }

}
