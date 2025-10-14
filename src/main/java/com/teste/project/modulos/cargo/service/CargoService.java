package com.teste.project.modulos.cargo.service;

import com.teste.project.modulos.cargo.dto.CargoRequest;
import com.teste.project.modulos.cargo.model.Cargo;
import com.teste.project.modulos.cargo.repository.CargoRepository;
import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.comum.exceptions.ValidacaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CargoService {

    private final CargoRepository repository;

    @Transactional
    public void cadastrar(CargoRequest request) {
        validarCargo(request.nome());
        var cargo = Cargo.of(request);
        repository.save(cargo);
    }

    public Page<Cargo> listarCargos() {
        var pageable = PageRequest.of(0, 20);
        return repository.findAll(pageable);
    }

    public Cargo getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Cargo não encontrado"));
    }

    private void validarCargo(String nome) {
        if(repository.existsByNome(nome)) {
            throw new ValidacaoException("Esse cargo já foi cadastrado!");
        }
    }

}
