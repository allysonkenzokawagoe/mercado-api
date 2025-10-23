package com.teste.project.modulos.filial.helper;

import com.teste.project.modulos.filiais.dto.FilialRequest;
import com.teste.project.modulos.filiais.model.Filial;
import lombok.experimental.UtilityClass;

import static com.teste.project.modulos.endereco.helper.EnderecoHelper.umEndereco;
import static com.teste.project.modulos.mercado.helper.MercadoHelper.umMercado;

@UtilityClass
public class FilialHelper {

    public static Filial umaFilial() {
        return new Filial(
                1,
                "123",
                umEndereco(),
                umMercado()
        );
    }

    public static FilialRequest umaFilialRequest() {
        return new FilialRequest(
                86600137,
                328,
                "123"
        );
    }
}
