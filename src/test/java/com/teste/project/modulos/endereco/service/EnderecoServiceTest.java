package com.teste.project.modulos.endereco.service;

import com.teste.project.modulos.endereco.dto.CepResponse;
import com.teste.project.modulos.endereco.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static com.teste.project.modulos.endereco.helper.EnderecoHelper.umCepResponse;
import static com.teste.project.modulos.endereco.helper.EnderecoHelper.umEndereco;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private EnderecoRepository repository;

    @InjectMocks
    private EnderecoService service;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(service, "url", "https://viacep.com.br/ws/%d/json/");
    }

    @Test
    void salvarEndereco_deveSalvarEndereco_quandoSolicitado() {
        when(restTemplate.getForObject("https://viacep.com.br/ws/86600137/json/",  CepResponse.class)).thenReturn(umCepResponse());

        assertThatCode(() -> service.salvarEndereco(328, 86600137)).doesNotThrowAnyException();

        verify(repository).save(umEndereco());
    }

    @Test
    void buscarEnderecoPorCep_DeveRetornarEndereco_quandoCepValido() {
        when(restTemplate.getForObject("https://viacep.com.br/ws/86600137/json/", CepResponse.class)).thenReturn(umCepResponse());

        assertThatCode(() -> service.buscarEnderecoPorCep(86600137)).doesNotThrowAnyException();
    }

}
