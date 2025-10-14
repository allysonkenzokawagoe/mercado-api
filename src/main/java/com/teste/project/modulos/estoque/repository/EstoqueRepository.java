package com.teste.project.modulos.estoque.repository;

import com.teste.project.modulos.estoque.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
    Estoque findByProdutoId(Integer produtoId);
}
