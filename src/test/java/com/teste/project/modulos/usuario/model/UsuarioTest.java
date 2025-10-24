package com.teste.project.modulos.usuario.model;

import com.teste.project.modulos.user.enums.ESituacao;
import com.teste.project.modulos.user.model.Usuario;
import org.junit.jupiter.api.Test;

import static com.teste.project.modulos.filial.helper.FilialHelper.umaFilial;
import static com.teste.project.modulos.usuario.helper.UsuarioHelper.umUsuarioRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class UsuarioTest {

    @Test
    void of_deveCriarUsuario_quandoSolicitado() {
        var usuario = Usuario.of(umUsuarioRequest(), umaFilial());

        assertThat(usuario.getNome()).isEqualTo("Allyson");
        assertThat(usuario.getEmail()).isEqualTo("allyson@teste.com");
        assertThat(usuario.getCpf()).isEqualTo(1323112123);
        assertThat(usuario.getSalario()).isEqualTo(1254.23);
        assertThat(usuario.getSituacao()).isEqualTo(ESituacao.ATIVO);
    }

}
