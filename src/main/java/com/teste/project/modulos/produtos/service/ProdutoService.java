package com.teste.project.modulos.produtos.service;

import com.teste.project.modulos.categoria.service.CategoriaService;
import com.teste.project.modulos.comum.dto.PageRequest;
import com.teste.project.modulos.comum.dto.PageResponse;
import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.comum.exceptions.ValidacaoException;
import com.teste.project.modulos.produtos.dto.ProdutoFiltro;
import com.teste.project.modulos.produtos.dto.ProdutoRequest;
import com.teste.project.modulos.produtos.dto.ProdutoRequestEdit;
import com.teste.project.modulos.produtos.dto.ProdutoResponse;
import com.teste.project.modulos.produtos.mapper.ProdutoMapper;
import com.teste.project.modulos.produtos.model.Produto;
import com.teste.project.modulos.produtos.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final CategoriaService categoriaService;
    private final ProdutoMapper mapper;

    @Transactional
    public void cadastrar(ProdutoRequest request) {
        validarProduto(request.nome(), request.marca());

        var produto = Produto.of(request);

        produto.setCategoria(categoriaService.getById(request.categoriaId()));

        repository.save(produto);
    }

    @Transactional
    public ProdutoResponse editar(ProdutoRequestEdit request, Integer id) {
        var produto = getById(id);
        mapper.map(request, produto);

        if(request.categoriaId() != null) {
            produto.setCategoria(categoriaService.getById(request.categoriaId()));
        }

        save(produto);
        System.out.println("Produto: " + produto.getNome());
        return mapper.toResponse(produto);
    }

    public PageResponse<ProdutoResponse> listarTodos(ProdutoFiltro filtros, PageRequest pageable) {
        var predicate = filtros.toPredicate();
        var produtosResponse = repository.findAll(predicate, pageable).map(mapper::toResponse);
        return PageResponse.from(produtosResponse);
    }

    public Produto getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }

    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    private void validarProduto(String nome, String marca) {
        if(repository.existsByNomeAndMarca(nome, marca)) {
            throw new ValidacaoException("Produto já existente");
        }
    }
}
