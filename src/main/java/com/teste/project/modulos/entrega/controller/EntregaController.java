package com.teste.project.modulos.entrega.controller;

import com.teste.project.modulos.entrega.model.Entrega;
import com.teste.project.modulos.entrega.service.EntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/entrega")
public class EntregaController {

    private final EntregaService service;

    @GetMapping
    public List<Entrega> buscarTodos() {
        return service.buscarTodos();
    }

    @PutMapping("{id}/finalizar")
    public void finalizarEntrega(@PathVariable Integer id) {
        service.finalizarEntrega(id);
    }

    @PutMapping("{id}/cancelar")
    public void cancelarEntrega(@PathVariable Integer id) {
        service.cancelarEntrega(id);
    }
}
