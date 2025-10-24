package com.teste.project.modulos.produto.service;

import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.estoque.service.EstoqueService;
import com.teste.project.modulos.filiais.service.FilialService;
import com.teste.project.modulos.produtos.enums.ESituacaoProduto;
import com.teste.project.modulos.produtos.model.ProdutoFilial;
import com.teste.project.modulos.produtos.repository.ProdutoFilialRepository;
import com.teste.project.modulos.produtos.service.ProdutoFilialService;
import com.teste.project.modulos.produtos.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.teste.project.modulos.estoque.helper.EstoqueHelper.umEstoqueVazio;
import static com.teste.project.modulos.filial.helper.FilialHelper.umaFilial;
import static com.teste.project.modulos.produto.helper.ProdutoFilialHelper.umProdutoFilial;
import static com.teste.project.modulos.produto.helper.ProdutoFilialHelper.umProdutoFilialCriado;
import static com.teste.project.modulos.produto.helper.ProdutoHelper.umProduto;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProdutoFilialServiceTest {

    @Mock
    private ProdutoFilialRepository repository;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private EstoqueService estoqueService;

    @Mock
    private FilialService filialService;

    @InjectMocks
    private ProdutoFilialService service;

    @Test
    void cadastrar_deveCadastrarProdutoFilial_quandoSolicitado() {
        when(filialService.getById(1)).thenReturn(umaFilial());
        when(produtoService.getById(1)).thenReturn(umProduto());

        assertThatCode(() -> service.cadastrar(8.99, 1, 1)).doesNotThrowAnyException();

        verify(repository).save(umProdutoFilialCriado());
    }


    @Test
    void adicionarAoEstoque_deveAdicionarProdutoAoEstoque_quandoSolicitado() {
        when(estoqueService.getByProdutoId(1)).thenReturn(umEstoqueVazio());

        assertThatCode(() -> service.adicionarAoEstoque(1, 10.0)).doesNotThrowAnyException();

        var captor = ArgumentCaptor.forClass(ProdutoFilial.class);
        verify(repository).save(captor.capture());
    }

    @Test
    void adicionarEstoque_deveAlterarSituacaoParaForaEstoque_quandoQuantidadeIgualZero() {
        when(estoqueService.getByProdutoId(1)).thenReturn(umEstoqueVazio());

        assertThatCode(() -> service.adicionarAoEstoque(1, 0.0)).doesNotThrowAnyException();

        var captor = ArgumentCaptor.forClass(ProdutoFilial.class);
        verify(repository).save(captor.capture());

        var salvo = captor.getValue();
        assertEquals(ESituacaoProduto.FORA_ESTOQUE, salvo.getSituacao());
    }

    @Test
    void getById_deveRetornarProdutoFilial_quandoExistir() {
        when(repository.findById(1)).thenReturn(Optional.of(umProdutoFilial()));

        assertThatCode(() -> service.getById(1)).doesNotThrowAnyException();
    }

    @Test
    void getById_deveLancarNotFoundException_quandoNaoExistir() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThatCode(() -> service.getById(1))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Produto não encontrado nesta filial");
    }

    @Test
    void salvar_deveSalvarProdutoFilial_quandoSolicitado() {
        assertThatCode(() -> service.salvar(umProdutoFilial())).doesNotThrowAnyException();

        verify(repository).save(umProdutoFilial());
    }
}
