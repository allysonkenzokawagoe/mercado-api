package com.teste.project.modulos.usuario.helper;

import com.teste.project.modulos.user.enums.ESituacao;
import com.teste.project.modulos.user.model.Usuario;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

import static com.teste.project.modulos.cargo.helper.CargoHelper.umCargo;
import static com.teste.project.modulos.endereco.helper.EnderecoHelper.umEndereco;
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
}
