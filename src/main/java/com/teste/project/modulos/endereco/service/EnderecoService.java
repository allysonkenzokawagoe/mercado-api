package com.teste.project.modulos.endereco.service;

import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.endereco.dto.CepResponse;
import com.teste.project.modulos.endereco.model.Endereco;
import com.teste.project.modulos.endereco.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class EnderecoService {

    @Value("${app-config.cep.url}")
    private String url;
    private final EnderecoRepository repository;
    private final RestTemplate restTemplate;

    public Endereco salvarEndereco(Integer numero, Integer cep) {
        var endereco = Endereco.of(buscarEnderecoPorCep(cep), numero);
        return repository.save(endereco);
    }

    public Endereco getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
    }

    public CepResponse buscarEnderecoPorCep(Integer cep){
        var urlFormatada = String.format(url, cep);
        return restTemplate.getForObject(urlFormatada, CepResponse.class);
    }

}
