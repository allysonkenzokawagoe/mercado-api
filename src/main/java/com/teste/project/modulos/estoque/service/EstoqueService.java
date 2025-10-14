package com.teste.project.modulos.estoque.service;

import com.teste.project.modulos.estoque.model.Estoque;
import com.teste.project.modulos.estoque.repository.EstoqueRepository;
import com.teste.project.modulos.produtos.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EstoqueService {

    private final EstoqueRepository repository;

    public void criarEstoque(Produto produto) {
        var estoque = Estoque.of(produto);
        repository.save(estoque);
    }

    @Transactional
    public void adicionarEstoque(Integer produtoId, Double quantidade) {
        var estoque = getByProdutoId(produtoId);
        estoque.setQuantidade(quantidade);
        estoque.setUltimaAtualizacao(LocalDateTime.now());

        salvar(estoque);
    }

    public List<Estoque> buscarTodos() {
        return repository.findAll();
    }

    public Estoque getByProdutoId(Integer produtoId) {
        return repository.findByProdutoId(produtoId);
    }

    public Estoque salvar(Estoque estoque) {
        return repository.save(estoque);
    }

}
