package com.teste.project.modulos.estoque.service;

import com.teste.project.modulos.comum.dto.PageResponse;
import com.teste.project.modulos.estoque.dto.EstoqueResponse;
import com.teste.project.modulos.estoque.mapper.EstoqueMapper;
import com.teste.project.modulos.estoque.model.Estoque;
import com.teste.project.modulos.estoque.repository.EstoqueRepository;
import com.teste.project.modulos.filiais.model.Filial;
import com.teste.project.modulos.produtos.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class EstoqueService {

    private final EstoqueRepository repository;
    private final EstoqueMapper mapper;

    public void criarEstoque(Produto produto, Filial filial) {
        var estoque = Estoque.of(produto, filial);
        salvar(estoque);
    }

    public void adicionarEstoque(Integer produtoId, Double quantidade) {
        var estoque = getByProdutoId(produtoId);
        estoque.setQuantidade(quantidade);
        estoque.setUltimaAtualizacao(LocalDateTime.now());

        salvar(estoque);
    }

    public PageResponse<EstoqueResponse> buscarTodos() {
        var pageRequest = PageRequest.of(0, 20);
        var estoque = repository.findAll(pageRequest).map(mapper::toResponse);
        return PageResponse.from(estoque);
    }

    public Estoque getByProdutoId(Integer produtoId) {
        return repository.findByProdutoId(produtoId);
    }

    public Estoque salvar(Estoque estoque) {
        return repository.save(estoque);
    }

}
