package com.teste.project.modulos.mercado.service;

import com.teste.project.modulos.comum.exceptions.ValidacaoException;
import com.teste.project.modulos.mercado.model.Mercado;
import com.teste.project.modulos.mercado.repository.MercadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MercadoService {

    private final MercadoRepository repository;

    public void salvar(String nome) {
        var mercado = Mercado.of(nome);

        repository.save(mercado);
    }

    public Mercado getMercado(Integer mercadoId) {
        return repository.findById(mercadoId).orElseThrow(() -> new ValidacaoException("Mercado não encontrado"));
    }

}
