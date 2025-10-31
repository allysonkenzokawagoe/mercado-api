package com.teste.project.modulos.entrega.service;

import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.entrega.enums.ESituacaoEntrega;
import com.teste.project.modulos.entrega.model.Entrega;
import com.teste.project.modulos.entrega.repository.EntregaRepository;
import com.teste.project.modulos.produtos.dto.VendaDto;
import com.teste.project.modulos.venda.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EntregaService {

    private final EntregaRepository repository;
    private final VendaService vendaService;

    @Transactional
    @RabbitListener(queues = "pedido.sucesso")
    public void gerarEntrega(VendaDto venda) {
        var entrega = Entrega.of(venda);
        entrega.setVenda(vendaService.getById(venda.id()));
        repository.save(entrega);
    }

    @Transactional
    public void finalizarEntrega(Integer id) {
        var entrega = getById(id);
        entrega.setSituacao(ESituacaoEntrega.ENTREGA_FINALIZADA);
        repository.save(entrega);
    }

    @Transactional
    public void cancelarEntrega(Integer id) {
        var entrega = getById(id);
        entrega.setSituacao(ESituacaoEntrega.ENTREGA_CANCELADA);
        repository.save(entrega);
    }

    public Entrega getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Entrega não encontrada"));
    }

    public List<Entrega> buscarTodos() {
        return repository.findAll();
    }
}
