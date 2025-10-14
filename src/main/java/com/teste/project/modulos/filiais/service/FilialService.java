package com.teste.project.modulos.filiais.service;

import com.teste.project.modulos.comum.exceptions.ValidacaoException;
import com.teste.project.modulos.endereco.service.EnderecoService;
import com.teste.project.modulos.filiais.dto.FilialRequest;
import com.teste.project.modulos.filiais.model.Filial;
import com.teste.project.modulos.filiais.repository.FilialRepository;
import com.teste.project.modulos.mercado.service.MercadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FilialService {

    private final FilialRepository repository;
    private final EnderecoService enderecoService;
    private final MercadoService mercadoService;

    @Transactional
    public void salvar(FilialRequest filialRequest, Integer mercadoId) {
        var endereco = enderecoService.salvarEndereco(filialRequest.numero(), filialRequest.cep());
        var mercado = mercadoService.getMercado(mercadoId);
        var filial = Filial.of(endereco, mercado);
        repository.save(filial);
    }

    public Filial getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ValidacaoException("Filian não encontrada"));
    }

}
