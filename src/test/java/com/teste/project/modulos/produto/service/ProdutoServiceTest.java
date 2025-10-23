package com.teste.project.modulos.produto.service;

import com.teste.project.modulos.categoria.service.CategoriaService;
import com.teste.project.modulos.comum.exceptions.ValidacaoException;
import com.teste.project.modulos.estoque.repository.EstoqueRepository;
import com.teste.project.modulos.estoque.service.EstoqueService;
import com.teste.project.modulos.filiais.service.FilialService;
import com.teste.project.modulos.produtos.mapper.ProdutoMapper;
import com.teste.project.modulos.produtos.model.Produto;
import com.teste.project.modulos.produtos.repository.ProdutoRepository;
import com.teste.project.modulos.produtos.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.teste.project.modulos.categoria.helper.CategoriaHelper.umaCategoria;
import static com.teste.project.modulos.produto.helper.ProdutoHelper.*;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private FilialService filialService;

    @Mock
    private CategoriaService categoriaService;

    @Mock
    private EstoqueService estoqueService;

    @Mock
    private EstoqueRepository estoqueRepository;

    @Mock
    private ProdutoMapper mapper;

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    @Test
    void cadastrar_deveCadastrarProduto_quandoSolicitado() {
        when(repository.existsByNomeAndMarca("Bolacha", "Bauduco")).thenReturn(false);
        when(categoriaService.getById(1)).thenReturn(umaCategoria());

        assertThatCode(() -> service.cadastrar(umProdutoRequest())).doesNotThrowAnyException();

        var captor = ArgumentCaptor.forClass(Produto.class);
        verify(repository).save(captor.capture());

        var salvo = captor.getValue();

        assertEquals("Bolacha", salvo.getNome());
    }

    @Test
    void cadastrar_deveLancarValidacaoException_quandoProdutoJaCadastrado() {
        when(repository.existsByNomeAndMarca("Bolacha", "Bauduco")).thenReturn(true);

        assertThatCode(() -> service.cadastrar(umProdutoRequest()))
                .isInstanceOf(ValidacaoException.class)
                .hasMessage("Produto já existente");

        verify(repository, never()).save(any(Produto.class));
    }

    @Test
    void editarProduto_deveEditarProduto_quandoSolicitado() {
        var produto = umProduto();
        var requestEdit = umProdutoRequestEdit();

        when(repository.findById(1)).thenReturn(Optional.of(produto));
        when(categoriaService.getById(1)).thenReturn(umaCategoria());

        assertThatCode(() -> service.editar(requestEdit, 1)).doesNotThrowAnyException();

        verify(mapper).map(requestEdit, produto);
        verify(repository).save(produto);
    }
}
