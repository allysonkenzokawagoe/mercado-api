package com.teste.project.modulos.cargo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teste.project.modulos.cargo.dto.CargoRequest;
import com.teste.project.modulos.comum.enums.EPermissao;
import com.teste.project.modulos.user.model.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"usuario"})
@Table(name = "CARGO")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "PERMISSOES")
    @Enumerated(EnumType.STRING)
    private List<EPermissao> permissoes;

    @OneToMany(mappedBy = "cargo")
    @JsonIgnore
    private List<Usuario> usuario;

    public static Cargo of(CargoRequest request) {
        return Cargo.builder()
                .nome(request.nome())
                .permissoes(request.permissoes())
                .build();
    }
}
