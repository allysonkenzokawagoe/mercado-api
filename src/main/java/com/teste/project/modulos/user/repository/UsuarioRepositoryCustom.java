package com.teste.project.modulos.user.repository;

import com.querydsl.core.types.Predicate;
import com.teste.project.modulos.user.dto.UsuarioResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface UsuarioRepositoryCustom {
    Page<UsuarioResponse> findAllByPredicate(Predicate predicate, PageRequest pageable);

}
