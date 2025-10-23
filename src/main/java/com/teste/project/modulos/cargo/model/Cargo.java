package com.teste.project.modulos.cargo.model;

import com.teste.project.modulos.cargo.dto.CargoRequest;
import com.teste.project.modulos.comum.enums.EPermissao;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CARGO")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "PERMISSOES")
    @Enumerated(EnumType.STRING)
    private EPermissao permissao;

    public static Cargo of(CargoRequest request) {
        return Cargo.builder()
                .nome(request.nome())
                .permissao(request.permissao())
                .build();
    }
}
