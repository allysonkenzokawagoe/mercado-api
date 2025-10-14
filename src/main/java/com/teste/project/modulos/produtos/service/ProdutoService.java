package com.teste.project.modulos.produtos.service;

import com.teste.project.modulos.categoria.service.CategoriaService;
import com.teste.project.modulos.comum.dto.PageResponse;
import com.teste.project.modulos.comum.exceptions.ValidacaoException;
import com.teste.project.modulos.estoque.service.EstoqueService;
import com.teste.project.modulos.filiais.service.FilialService;
import com.teste.project.modulos.produtos.dto.ProdutoRequest;
import com.teste.project.modulos.produtos.dto.ProdutoRequestEdit;
import com.teste.project.modulos.produtos.dto.ProdutoResponse;
import com.teste.project.modulos.produtos.enums.ESituacaoProduto;
import com.teste.project.modulos.produtos.mapper.ProdutoMapper;
import com.teste.project.modulos.produtos.model.Produto;
import com.teste.project.modulos.produtos.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final EstoqueService estoqueService;
    private final CategoriaService categoriaService;
    private final FilialService filialService;
    private final ProdutoMapper mapper;

    @Transactional
    public void cadastrar(ProdutoRequest request, Integer filialId) {
        validarProduto(request.nome(), request.marca());

        var produto = Produto.of(request);
        var filial = filialService.getById(filialId);

        produto.setCategoria(categoriaService.getById(request.categoriaId()));

        repository.save(produto);
        estoqueService.criarEstoque(produto, filial);
    }

    @Transactional
    public void adicionarAoEstoque(Integer id, Double quantidade) {
        var produto = getById(id);

        estoqueService.adicionarEstoque(id, quantidade);

        validarSituacao(produto);
        save(produto);
    }

    @Transactional
    public ProdutoResponse editar(ProdutoRequestEdit request, Integer id) {
        var produto = getById(id);
        mapper.map(request, produto);

        if(request.categoriaId() != null) {
            produto.setCategoria(categoriaService.getById(request.categoriaId()));
        }

        save(produto);
        return mapper.toResponse(produto);
    }

    public PageResponse<ProdutoResponse> listarTodos() {
        var pageable = PageRequest.of(0, 10);
        var produtosResponse = repository.findAll(pageable).map(mapper::toResponse);
        return PageResponse.from(produtosResponse);
    }

    public Produto getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ValidacaoException("Produto não encontrado"));
    }

    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    private void validarProduto(String nome, String marca) {
        if(repository.existsByNomeAndMarca(nome, marca)) {
            throw new ValidationException("Produto já existente");
        }
    }

    private void validarSituacao(Produto produto) {
        if(produto.getEstoque().getQuantidade() == 0) {
            produto.setSituacao(ESituacaoProduto.FORA_ESTOQUE);
        } else if(produto.getEstoque().getQuantidade() > 0) {
            produto.setSituacao(ESituacaoProduto.EM_ESTOQUE);
        } else if(produto.getEstoque().getQuantidade() < 0) {
            throw new ValidationException("O estoque não pode ser menor do que 0");
        }
    }
}
