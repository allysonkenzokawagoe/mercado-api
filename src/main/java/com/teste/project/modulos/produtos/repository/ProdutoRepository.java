package com.teste.project.modulos.produtos.repository;

import com.teste.project.modulos.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    boolean existsByNomeAndMarca(String nome, String marca);
}
