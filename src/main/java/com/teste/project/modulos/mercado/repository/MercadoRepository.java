package com.teste.project.modulos.mercado.repository;

import com.teste.project.modulos.mercado.model.Mercado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MercadoRepository extends JpaRepository<Mercado, Integer> {
}
