package com.teste.project.modulos.endereco.mapper;

import com.teste.project.modulos.endereco.dto.EnderecoResponse;
import com.teste.project.modulos.endereco.model.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoResponse toResponse(Endereco endereco);

}
