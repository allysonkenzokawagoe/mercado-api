package com.teste.project.modulos.autenticacao.helper;

import com.teste.project.modulos.autenticacao.dto.UsuarioAutenticado;
import com.teste.project.modulos.comum.enums.EPermissao;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class AutenticacaoHelper {

    public static UsuarioAutenticado umUsuarioAutenticado() {
        return new UsuarioAutenticado(
                "token",
                "1",
                "Allyson",
                "allyson@teste.com",
                EPermissao.ADMIN,
                List.of("Administrador")
        );
    }

}
