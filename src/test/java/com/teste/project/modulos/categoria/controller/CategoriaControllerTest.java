package com.teste.project.modulos.categoria.controller;

import com.teste.project.modulos.autenticacao.filter.AuthTokenFilter;
import com.teste.project.modulos.categoria.service.CategoriaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.teste.project.config.TestRequisitionHelper.isBadRequest;
import static com.teste.project.config.TestRequisitionHelper.isOk;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(CategoriaController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CategoriaControllerTest {

    private static final String API_URL = "/api/categoria";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthTokenFilter authTokenFilter;

    @MockitoBean
    private CategoriaService service;

    @Test
    void cadastrar_deveRetornarOk_quandoRequestValida() {
        isOk(post(API_URL), mockMvc, "Alimento");
        verify(service).cadastrar(anyString());
    }

    @Test
    void cadastrar_deveRetornarBadRequest_quandoRequestInvalido() {
        isBadRequest(post(API_URL), mockMvc);
        verifyNoInteractions(service);
    }

}
