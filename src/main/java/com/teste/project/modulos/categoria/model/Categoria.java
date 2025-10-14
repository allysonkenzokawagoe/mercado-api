package com.teste.project.modulos.categoria.model;

import com.teste.project.modulos.produtos.model.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CATEGORIA")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;

    public static Categoria of(String nome) {
        return Categoria.builder().nome(nome).build();
    }
}
