package com.teste.project.config;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.teste.project.config.TestHelper.convertObjectToJsonBytes;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@UtilityClass
public class TestRequisitionHelper {

    @SneakyThrows
    public static void isOk(MockHttpServletRequestBuilder endpoint, MockMvc mvc) {
        mvc.perform(endpoint
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    public static void isOk(MockHttpServletRequestBuilder endpoint, MockMvc mvc, Object object) {
        mvc.perform(endpoint
                .content(convertObjectToJsonBytes(object))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    public static void isBadRequest(MockHttpServletRequestBuilder endpoint, MockMvc mvc) {
        mvc.perform(endpoint
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
