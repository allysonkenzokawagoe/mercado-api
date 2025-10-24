package com.teste.project.modulos.endereco.helper;

import com.teste.project.modulos.endereco.dto.CepRequest;
import com.teste.project.modulos.endereco.dto.CepResponse;
import com.teste.project.modulos.endereco.dto.EnderecoRequest;
import com.teste.project.modulos.endereco.dto.EnderecoResponse;
import com.teste.project.modulos.endereco.model.Endereco;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnderecoHelper {

    public static Endereco umEndereco() {
        return new Endereco(
                null,
                "Avenida Interventor Manoel Ribas",
                328,
                "Rolândia",
                "PR",
                "Paraná",
                "86600-137"
        );
    }

    public static EnderecoRequest umEnderecoRequest() {
        return new EnderecoRequest(328);
    }

    public static EnderecoResponse umEnderecoResponse() {
        return new EnderecoResponse(
                "Avenida Interventor Manoel Ribas",
                328,
                "Rolândia",
                "PR",
                "Paraná",
                "86600-137"
        );
    }

    public static CepResponse umCepResponse() {
        return new CepResponse(
                "86600-137",
                "Rolândia",
                "Avenida Interventor Manoel Ribas",
                "PR",
                "Paraná"
        );
    }

    public static CepRequest umCepRequest() {
        return new CepRequest(86600137);
    }

}
