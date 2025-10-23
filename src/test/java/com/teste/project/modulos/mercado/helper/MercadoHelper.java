package com.teste.project.modulos.mercado.helper;

import com.teste.project.modulos.mercado.dto.MercadoRequest;
import com.teste.project.modulos.mercado.model.Mercado;
import lombok.experimental.UtilityClass;

import java.util.List;

import static com.teste.project.modulos.filial.helper.FilialHelper.umaFilial;

@UtilityClass
public class MercadoHelper {

    public static Mercado umMercado() {
        return new Mercado(
                null,
                "Mercado 1",
                "123"
        );
    }

    public static MercadoRequest umMercadoRequest() {
        return new MercadoRequest(
                "Mercado 1",
                "123"
        );
    }
}
