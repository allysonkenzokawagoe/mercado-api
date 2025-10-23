package com.teste.project.modulos.cargo.controller;

import com.teste.project.modulos.cargo.dto.CargoRequest;
import com.teste.project.modulos.cargo.model.Cargo;
import com.teste.project.modulos.cargo.service.CargoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cargo")
public class CargoController {

    private final CargoService service;

    @PostMapping
    public void cadastrar(@RequestBody @Valid CargoRequest request) {
        service.cadastrar(request);
    }

    @GetMapping
    public Page<Cargo> listarTodos() {
        return service.listarCargos();
    }

}
