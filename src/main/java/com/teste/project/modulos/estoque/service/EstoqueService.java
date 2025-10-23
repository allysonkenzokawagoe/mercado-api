package com.teste.project.modulos.estoque.service;

import com.teste.project.modulos.comum.dto.PageResponse;
import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.estoque.dto.EstoqueResponse;
import com.teste.project.modulos.estoque.mapper.EstoqueMapper;
import com.teste.project.modulos.estoque.model.Estoque;
import com.teste.project.modulos.estoque.repository.EstoqueRepository;
import com.teste.project.modulos.filiais.model.Filial;
import com.teste.project.modulos.produtos.model.ProdutoFilial;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class EstoqueService {

    private final EstoqueRepository repository;
    private final EstoqueMapper mapper;

    public void criarEstoque(ProdutoFilial produtoFilial, Filial filial) {
        var estoque = Estoque.of(produtoFilial, filial);
        salvar(estoque);
    }

    public void adicionarEstoque(Integer produtoFilialId, Double quantidade) {
        var estoque = getByProdutoId(produtoFilialId);
        estoque.setQuantidade(quantidade);
        estoque.setUltimaAtualizacao(LocalDateTime.now());

        salvar(estoque);
    }

    public PageResponse<EstoqueResponse> buscarTodos() {
        var pageRequest = PageRequest.of(0, 20);
        var estoque = repository.findAll(pageRequest).map(mapper::toResponse);
        return PageResponse.from(estoque);
    }

    public Estoque getByProdutoId(Integer produtoFilialId) {
        return repository.findByProdutoFilialId(produtoFilialId).orElseThrow(() -> new NotFoundException("Estoque não encontrado"));
    }

    public Estoque salvar(Estoque estoque) {
        return repository.save(estoque);
    }

}
