package com.teste.project.modulos.produtos.mapper;

import com.teste.project.modulos.produtos.dto.ProdutoRequestEdit;
import com.teste.project.modulos.produtos.dto.ProdutoResponse;
import com.teste.project.modulos.produtos.model.Produto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(target = "categoriaNome", source = "categoria.nome")
    ProdutoResponse toResponse(Produto produto);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE
    )
    void map(ProdutoRequestEdit requestEdit, @MappingTarget Produto produto);

}
