package com.teste.project.modulos.cargo.repository;

import com.teste.project.modulos.cargo.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer>, QuerydslPredicateExecutor<Cargo> {
    boolean existsByNome(String nome);
}
