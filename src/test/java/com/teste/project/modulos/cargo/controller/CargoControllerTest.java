package com.teste.project.modulos.cargo.controller;

import com.teste.project.config.CustomUserDetailsService;
import com.teste.project.config.SecurityConfig;
import com.teste.project.modulos.autenticacao.filter.AuthTokenFilter;
import com.teste.project.modulos.cargo.repository.CargoRepository;
import com.teste.project.modulos.cargo.service.CargoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.teste.project.config.TestRequisitionHelper.isBadRequest;
import static com.teste.project.config.TestRequisitionHelper.isOk;
import static com.teste.project.modulos.cargo.helper.CargoHelper.umCargoRequest;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(CargoController.class)
@Import(SecurityConfig.class)
public class CargoControllerTest {

    private static final String API_URL = "/api/cargo";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CargoService service;

    @MockitoBean
    private CargoRepository repository;

    @MockitoBean
    private CustomUserDetailsService userDetailsService;

    @MockitoBean
    private AuthTokenFilter authTokenFilter;

    @WithAnonymousUser
    @Test
    void cadastrar_deveRetornarOk_quandoRequestValida() {
        isOk(post(API_URL), mockMvc, umCargoRequest());
        verify(service).cadastrar(umCargoRequest());
    }

    @Test
    void cadastrar_deveRetornarBadRequest_quandoRequestInvalido() {
        isBadRequest(post(API_URL), mockMvc);
        verifyNoInteractions(service);
    }
}
