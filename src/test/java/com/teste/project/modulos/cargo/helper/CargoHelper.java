package com.teste.project.modulos.cargo.helper;

import com.teste.project.modulos.cargo.dto.CargoRequest;
import com.teste.project.modulos.cargo.dto.CargoResponse;
import com.teste.project.modulos.cargo.model.Cargo;
import com.teste.project.modulos.comum.enums.EPermissao;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@UtilityClass
public class CargoHelper {

    public static Cargo umCargo() {
        return new Cargo(
                null,
                "Administrador",
                EPermissao.ADMIN
        );
    }

    public static CargoRequest umCargoRequest() {
        return new CargoRequest(
                "Administrador",
                EPermissao.ADMIN
        );
    }

    public static CargoResponse umCargoResponse() {
        return new CargoResponse(
                "Administrador"
        );
    }

    public static Page<Cargo> umCargoPage() {
        return new PageImpl<>(List.of(umCargo()));
    }

}
