package com.teste.project.modulos.produtos.service;

import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.estoque.model.Estoque;
import com.teste.project.modulos.estoque.service.EstoqueService;
import com.teste.project.modulos.filiais.service.FilialService;
import com.teste.project.modulos.produtos.enums.ESituacaoProduto;
import com.teste.project.modulos.produtos.model.ProdutoFilial;
import com.teste.project.modulos.produtos.repository.ProdutoFilialRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProdutoFilialService {

    private final FilialService filialService;
    private final ProdutoService produtoService;
    private final EstoqueService estoqueService;
    private final ProdutoFilialRepository repository;

    public ProdutoFilial cadastrar(Double preco, Integer filialId, Integer produtoId) {
        var produtoFilial = ProdutoFilial.of(preco);
        var filial = filialService.getById(filialId);
        produtoFilial.setFilial(filial);
        produtoFilial.setProduto(produtoService.getById(produtoId));

        salvar(produtoFilial);
        estoqueService.criarEstoque(produtoFilial, filial);
        return produtoFilial;
    }

    @Transactional
    public Estoque adicionarAoEstoque(Integer estoqueId, Double quantidade) {
        var estoque = estoqueService.getByProdutoId(estoqueId);
        var produtoFilial = estoque.getProdutoFilial();

        estoqueService.adicionarEstoque(produtoFilial.getId(), quantidade);

        validarSituacao(estoque, produtoFilial);
        salvar(produtoFilial);
        return estoque;
    }

    public ProdutoFilial findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado nesta filial"));
    }

    public ProdutoFilial salvar(ProdutoFilial produtoFilial) {
        return repository.save(produtoFilial);
    }

    private void validarSituacao(Estoque estoque, ProdutoFilial produtoFilial) {
        if(estoque.getQuantidade() == 0) {
            produtoFilial.setSituacao(ESituacaoProduto.FORA_ESTOQUE);
        } else if(estoque.getQuantidade() > 0) {
            produtoFilial.setSituacao(ESituacaoProduto.EM_ESTOQUE);
        } else if(estoque.getQuantidade() < 0) {
            throw new ValidationException("O estoque não pode ser menor do que 0");
        }
    }

}
