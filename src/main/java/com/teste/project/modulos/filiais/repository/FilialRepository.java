package com.teste.project.modulos.filiais.repository;

import com.teste.project.modulos.filiais.model.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Integer> {
}
