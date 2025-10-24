package com.teste.project.modulos.usuario.helper;

import com.querydsl.core.types.Predicate;
import com.teste.project.modulos.user.dto.UsuarioFiltro;
import com.teste.project.modulos.user.dto.UsuarioRequest;
import com.teste.project.modulos.user.dto.UsuarioRequestEdit;
import com.teste.project.modulos.user.dto.UsuarioResponse;
import com.teste.project.modulos.user.enums.ESituacao;
import com.teste.project.modulos.user.model.Usuario;
import com.teste.project.modulos.user.predicate.UsuarioPredicate;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

import static com.teste.project.modulos.cargo.helper.CargoHelper.umCargo;
import static com.teste.project.modulos.cargo.helper.CargoHelper.umCargoResponse;
import static com.teste.project.modulos.endereco.helper.EnderecoHelper.*;
import static com.teste.project.modulos.filial.helper.FilialHelper.umaFilial;

@UtilityClass
public class UsuarioHelper {

    public static Usuario umUsuario() {
        return new Usuario(
                "1",
                "Allyson",
                LocalDate.of(2004, 07, 25),
                1323112123,
                "allyson@teste.com",
                "123",
                1254.23,
                ESituacao.ATIVO,
                umEndereco(),
                umCargo(),
                umaFilial()
        );
    }

    public static UsuarioRequest umUsuarioRequest() {
        return new UsuarioRequest(
                "Allyson",
                "allyson@teste.com",
                "123",
                1323112123,
                1,
                1254.23,
                umCepRequest(),
                umEnderecoRequest()
        );
    }

    public static UsuarioRequestEdit umUsuarioRequestEdit() {
        return new UsuarioRequestEdit(
                "Pedro",
                null,
                null,
                null,
                null,
                null
        );
    }

    public static UsuarioResponse umUsuarioResponse() {
        return new UsuarioResponse(
                "1",
                "Allyson",
                "allyson@teste.com",
                "123",
                LocalDate.of(2004, 07, 25),
                1323112123,
                umEnderecoResponse(),
                umCargoResponse()
                );
    }

    public static UsuarioFiltro umUsuarioFiltro() {
        return new UsuarioFiltro("", null);
    }

    public static Predicate umUsuarioPredicate() {
        return new UsuarioPredicate().build();
    }
}
