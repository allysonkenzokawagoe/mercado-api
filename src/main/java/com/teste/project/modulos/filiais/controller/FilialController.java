package com.teste.project.modulos.filiais.controller;

import com.teste.project.modulos.filiais.dto.FilialRequest;
import com.teste.project.modulos.filiais.service.FilialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/filial")
public class FilialController {

    private final FilialService filialService;

    @PostMapping("{mercadoId}")
    public void salvar(@RequestBody FilialRequest filialRequest, @PathVariable Integer mercadoId) {
        filialService.salvar(filialRequest, mercadoId);
    }

}
