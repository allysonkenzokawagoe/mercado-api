package com.teste.project.modulos.endereco.controller;

import com.teste.project.modulos.endereco.dto.CepResponse;
import com.teste.project.modulos.endereco.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping
    public CepResponse pegarCep(@RequestParam Integer cep){
        return enderecoService.buscarEnderecoPorCep(cep);
    }

}
