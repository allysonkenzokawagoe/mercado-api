package com.teste.project.modulos.estoque.mapper;

import com.teste.project.modulos.estoque.dto.EstoqueResponse;
import com.teste.project.modulos.estoque.model.Estoque;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstoqueMapper {

    @Mapping(target = "produtoNome", source = "produtoFilial.produto.nome")
    EstoqueResponse toResponse(Estoque estoque);

}
