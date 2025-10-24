package com.teste.project.modulos.user.repository;

import com.teste.project.modulos.user.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>, UsuarioRepositoryCustom {
    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);
    Page<Usuario> findAllByCargoId(Integer cargoId, Pageable pageable);
}
