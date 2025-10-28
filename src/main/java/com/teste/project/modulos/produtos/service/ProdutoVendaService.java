package com.teste.project.modulos.produtos.service;

import com.teste.project.modulos.endereco.service.EnderecoService;
import com.teste.project.modulos.estoque.service.EstoqueService;
import com.teste.project.modulos.produtos.dto.VendaDto;
import com.teste.project.modulos.produtos.model.ProdutoVenda;
import com.teste.project.modulos.produtos.repository.ProdutoVendaRepository;
import com.teste.project.modulos.venda.enums.ETipoPagamento;
import com.teste.project.modulos.venda.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProdutoVendaService {

    private final ProdutoVendaRepository repository;
    private final ProdutoFilialService produtoFilialService;
    private final VendaService vendaService;
    private final EstoqueService estoqueService;
    private final EnderecoService enderecoService;
    private final RabbitTemplate rabbitTemplate;

    @Transactional
    public void cadastrarProdutoVenda(Integer vendaId, Integer produtoFilialId, Double quantidade) {
        var venda = vendaService.getById(vendaId);
        var produto = produtoFilialService.getById(produtoFilialId);
        var valor = quantidade * produto.getPreco();
        var produtoVenda = ProdutoVenda.of(produto, venda, quantidade, valor);
        var estoque = estoqueService.getByProdutoId(produtoFilialId);

        estoque.setQuantidade(estoque.getQuantidade() - quantidade);

        estoqueService.salvar(estoque);
        repository.save(produtoVenda);
    }

    @Transactional
    public void finalizarVenda(Integer vendaId, Integer enderecoId, ETipoPagamento tipoPagamento) {
        var produtosVenda = getProdutosVendidos(vendaId);
        var venda = vendaService.getById(vendaId);
        var endereco = enderecoService.getById(enderecoId);

        produtosVenda.forEach(prod ->
                venda.setValor(venda.getValor() + prod.getSubTotal())
        );

        venda.setTipoPagamento(tipoPagamento);
        venda.setDataVenda(LocalDateTime.now());
        venda.setProdutoVendidos(produtosVenda);
        venda.setEndereco(endereco);

        vendaService.processarPagamento();
        vendaService.save(venda);

        var vendaDto = VendaDto.of(venda);
        rabbitTemplate.convertAndSend("pedido.aberto", "pagamento.sucesso", vendaDto);
    }

    public List<ProdutoVenda> getProdutosVendidos(Integer vendaId) {
        return repository.findAllByVendaId(vendaId);
    }

    public ProdutoVenda save(ProdutoVenda produtoVenda) {
        return repository.save(produtoVenda);
    }

}
