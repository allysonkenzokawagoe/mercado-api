package com.teste.project.modulos.entrega.controller;

import com.teste.project.modulos.autenticacao.filter.AuthTokenFilter;
import com.teste.project.modulos.entrega.service.EntregaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.teste.project.config.TestRequisitionHelper.isOk;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(EntregaController.class)
@AutoConfigureMockMvc(addFilters = false)
public class EntregaControllerTest {

    private static final String API_URL = "/api/entrega";
    private static final String ADMIN = "ADMIN";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthTokenFilter authTokenFilter;

    @MockitoBean
    private EntregaService service;

    @Test
    @WithMockUser
    void buscarTodos_deveRetornarOk_quandoSolicitado() {
        isOk(get(API_URL), mockMvc);
        verify(service).buscarTodos();
    }
}
