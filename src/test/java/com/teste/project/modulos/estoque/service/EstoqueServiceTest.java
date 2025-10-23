package com.teste.project.modulos.estoque.service;

import com.teste.project.modulos.estoque.mapper.EstoqueMapper;
import com.teste.project.modulos.estoque.model.Estoque;
import com.teste.project.modulos.estoque.repository.EstoqueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.teste.project.modulos.estoque.helper.EstoqueHelper.umEstoque;
import static com.teste.project.modulos.filial.helper.FilialHelper.umaFilial;
import static com.teste.project.modulos.produto.helper.ProdutoFilialHelper.umProdutoFilial;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EstoqueServiceTest {

    @Mock
    private EstoqueRepository repository;

    @Mock
    private EstoqueMapper mapper;

    @InjectMocks
    private EstoqueService service;

    @Test
    void criarEstoque_deveCriarEstoque_quandoSolicitado() {
        var estoque = Estoque.of(umProdutoFilial(), umaFilial());

        assertThatCode(() -> service.criarEstoque(umProdutoFilial(), umaFilial())).doesNotThrowAnyException();

        verify(repository).save(estoque);
    }

    @Test
    void adicionarEstoque_deveAdicionarEstoque_quandoSolicitado() {
        when(repository.findByProdutoFilialId(1)).thenReturn(Optional.of(umEstoque()));

        assertThatCode(() -> service.adicionarEstoque(1, 10.0)).doesNotThrowAnyException();

        var captor = ArgumentCaptor.forClass(Estoque.class);
        verify(repository).save(captor.capture());
    }

    @Test
    void buscarTodos_deveRetornarTodosEstoques_quandoSolicitado() {
        var pageable = PageRequest.of(0, 20);

        when(repository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(umEstoque())));

        assertThatCode(() -> service.buscarTodos()).doesNotThrowAnyException();

        verify(repository).findAll(any(Pageable.class));
    }

}
