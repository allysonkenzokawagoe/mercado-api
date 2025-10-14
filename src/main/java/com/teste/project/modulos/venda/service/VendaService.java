package com.teste.project.modulos.venda.service;

import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.venda.model.Venda;
import com.teste.project.modulos.venda.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class VendaService {

    private final VendaRepository repository;

    @Transactional
    public void criarVenda() {
        var venda = Venda.of();
        repository.save(venda);
    }

    public void processarPagamento() {
        try{
            System.out.println("Pagamento Processado");
        } catch (Exception e){
            System.out.println("Erro ao processar pagamento");
        }
    }

    public Venda getById(Integer vendaId) {
        return repository.findById(vendaId).orElseThrow(() -> new NotFoundException("Venda não existente"));
    }

    public Venda save(Venda venda) {
        return repository.save(venda);
    }
}
