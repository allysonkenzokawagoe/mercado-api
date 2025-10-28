package com.teste.project.modulos.produto.service;

import com.teste.project.modulos.endereco.service.EnderecoService;
import com.teste.project.modulos.estoque.service.EstoqueService;
import com.teste.project.modulos.produtos.dto.VendaDto;
import com.teste.project.modulos.produtos.model.ProdutoVenda;
import com.teste.project.modulos.produtos.repository.ProdutoVendaRepository;
import com.teste.project.modulos.produtos.service.ProdutoFilialService;
import com.teste.project.modulos.produtos.service.ProdutoVendaService;
import com.teste.project.modulos.venda.enums.ETipoPagamento;
import com.teste.project.modulos.venda.model.Venda;
import com.teste.project.modulos.venda.service.VendaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

import static com.teste.project.modulos.estoque.helper.EstoqueHelper.umEstoque;
import static com.teste.project.modulos.produto.helper.ProdutoFilialHelper.umProdutoFilial;
import static com.teste.project.modulos.produto.helper.ProdutoVendaHelper.umProdutoVenda;
import static com.teste.project.modulos.venda.helper.VendaHelper.umaVenda;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProdutoVendaServiceTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private ProdutoVendaRepository repository;

    @Mock
    private ProdutoFilialService produtoFilialService;

    @Mock
    private VendaService vendaService;

    @Mock
    private EnderecoService enderecoService;

    @Mock
    private EstoqueService estoqueService;

    @InjectMocks
    private ProdutoVendaService service;

    @Test
    void cadastrarProdutoVenda_deveCadastrarProdutoVenda_quandoSolicitado() {
        var venda = umaVenda();
        var produtoFilial = umProdutoFilial();

        when(vendaService.getById(1)).thenReturn(venda);
        when(produtoFilialService.getById(1)).thenReturn(produtoFilial);
        when(estoqueService.getByProdutoId(1)).thenReturn(umEstoque());

        assertThatCode(() -> service.cadastrarProdutoVenda(1, 1, 10.0)).doesNotThrowAnyException();

        var captor = ArgumentCaptor.forClass(ProdutoVenda.class);
        verify(repository).save(captor.capture());

        var salvo  = captor.getValue();
        assertEquals(89.90, salvo.getSubTotal());
    }

    @Test
    void finalizarVenda_deveFinalizarVenda_quandoSolicitado() {
        when(service.getProdutosVendidos(1)).thenReturn(List.of(umProdutoVenda()));
        when(vendaService.getById(1)).thenReturn(umaVenda());

        assertThatCode(() -> service.finalizarVenda(1, 1, ETipoPagamento.CARTAO_DEBITO)).doesNotThrowAnyException();

        var captor = ArgumentCaptor.forClass(Venda.class);
        verify(vendaService).save(captor.capture());

        var salvo  = captor.getValue();
        assertEquals(ETipoPagamento.CARTAO_DEBITO, salvo.getTipoPagamento());

        var vendaDto = VendaDto.of(salvo);
        verify(rabbitTemplate).convertAndSend("pedido.aberto", "pagamento.sucesso", vendaDto);
    }

    @Test
    void getProdutosVendidos_deveRetornarProdutoVendido_quandoAchar() {
        when(repository.findAllByVendaId(1)).thenReturn(List.of(umProdutoVenda()));

        assertThatCode(() -> service.getProdutosVendidos(1)).doesNotThrowAnyException();
    }

    @Test
    void save_deveSalvarProdutoVendido_quandoSolicitado() {
        assertThatCode(() -> service.save(umProdutoVenda())).doesNotThrowAnyException();

        verify(repository).save(umProdutoVenda());
    }

}
