package com.teste.project.modulos.produtos.repository;

import com.teste.project.modulos.produtos.model.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Integer> {
    List<ProdutoVenda> findAllByVendaId(Integer vendaId);
}
