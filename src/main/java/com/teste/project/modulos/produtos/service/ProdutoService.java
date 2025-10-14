package com.teste.project.modulos.produtos.service;

import com.teste.project.modulos.categoria.service.CategoriaService;
import com.teste.project.modulos.comum.exceptions.ValidacaoException;
import com.teste.project.modulos.estoque.service.EstoqueService;
import com.teste.project.modulos.produtos.dto.ProdutoRequest;
import com.teste.project.modulos.produtos.enums.ETipoMedida;
import com.teste.project.modulos.produtos.model.Produto;
import com.teste.project.modulos.produtos.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final EstoqueService estoqueService;
    private final CategoriaService categoriaService;

    @Transactional
    public void cadastrar(ProdutoRequest request) {
        validarProduto(request.nome(), request.marca());
        var produto = Produto.of(request);
        produto.setCategoria(categoriaService.getById(request.categoriaId()));
        repository.save(produto);
        estoqueService.criarEstoque(produto);
    }

    public Page<Produto> listarTodos() {
        var pageable = PageRequest.of(0, 10);
        return repository.findAll(pageable);
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
}
