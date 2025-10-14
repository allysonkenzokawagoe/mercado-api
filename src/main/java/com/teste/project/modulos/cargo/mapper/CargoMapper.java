package com.teste.project.modulos.cargo.mapper;

import com.teste.project.modulos.cargo.dto.CargoResponse;
import com.teste.project.modulos.cargo.model.Cargo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CargoMapper {

    CargoResponse toResponse(Cargo cargo);

}
