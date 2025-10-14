package com.teste.project.modulos.user.mapper;

import com.teste.project.modulos.cargo.mapper.CargoMapper;
import com.teste.project.modulos.endereco.mapper.EnderecoMapper;
import com.teste.project.modulos.user.dto.UsuarioResponse;
import com.teste.project.modulos.user.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EnderecoMapper.class, CargoMapper.class})
public interface UsuarioMapper {

    @Mapping(target = "endereco", source = "endereco")
    @Mapping(target = "cargo", source = "cargo")
    List<UsuarioResponse> toResponse(List<Usuario> usuario);

}
