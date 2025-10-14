package com.teste.project.modulos.user.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teste.project.modulos.user.dto.UsuarioResponse;
import com.teste.project.modulos.user.mapper.UsuarioMapper;
import com.teste.project.modulos.user.model.Usuario;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import static com.teste.project.modulos.user.model.QUsuario.usuario;
import static com.teste.project.modulos.endereco.model.QEndereco.endereco;
import static com.teste.project.modulos.cargo.model.QCargo.cargo;

@RequiredArgsConstructor
@Repository
public class UsuarioRepositoryCustomImpl implements UsuarioRepositoryCustom {

    private final EntityManager em;
    private final UsuarioMapper mapper;

    @Override
    public Page<UsuarioResponse> findAllByPredicate(Predicate predicate, PageRequest pageable) {
        var query = getUsuarioQuery(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return new PageImpl<>(mapper.toResponse(query.fetch()), pageable, query.fetch().size());

    }

    private JPAQuery<Usuario> getUsuarioQuery(Predicate predicate) {
        return new JPAQueryFactory(em)
                .selectDistinct(usuario)
                .from(usuario)
                .leftJoin(cargo).fetchJoin()
                .leftJoin(endereco).fetchJoin()
                .where(predicate);
    }

}
