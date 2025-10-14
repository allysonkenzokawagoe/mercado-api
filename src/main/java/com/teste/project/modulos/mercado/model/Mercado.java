package com.teste.project.modulos.mercado.model;

import com.teste.project.modulos.filiais.model.Filial;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "MERCADO")
public class Mercado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CNPJ")
    private Integer cpnj;

    @OneToMany
    private List<Filial> filials;

    public static Mercado of(String nome) {
        return Mercado.builder()
                .nome(nome)
                .build();
    }

}
