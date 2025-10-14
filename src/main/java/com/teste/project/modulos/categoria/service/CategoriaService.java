package com.teste.project.modulos.categoria.service;

import com.teste.project.modulos.categoria.model.Categoria;
import com.teste.project.modulos.categoria.repository.CategoriaRepository;
import com.teste.project.modulos.comum.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public void cadastrar(String nome) {
        var categoria = Categoria.of(nome);

        repository.save(categoria);
    }

    public Categoria getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
    }
}
