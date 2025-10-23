package com.teste.project.modulos.filiais.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.teste.project.modulos.endereco.model.Endereco;
import com.teste.project.modulos.mercado.model.Mercado;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"endereco"})
@Entity
@Table(name = "FILIAL")
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CNPJ")
    private String cnpj;

    @OneToOne
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "FK_MERCADO")
    @JsonBackReference
    private Mercado mercado;

    public static Filial of(Endereco endereco, String cnpj, Mercado mercado) {
        return Filial.builder()
                .endereco(endereco)
                .cnpj(cnpj)
                .mercado(mercado)
                .build();
    }
}
