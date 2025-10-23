package com.teste.project.modulos.mercado.service;

import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.mercado.dto.MercadoRequest;
import com.teste.project.modulos.mercado.model.Mercado;
import com.teste.project.modulos.mercado.repository.MercadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MercadoService {

    private final MercadoRepository repository;

    public void cadastrar(MercadoRequest request) {
        var mercado = Mercado.of(request);

        repository.save(mercado);
    }

    public Mercado getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Mercado não encontrado"));
    }
}
