package com.teste.project.modulos.estoque.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teste.project.modulos.produtos.model.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ESTOQUE")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "QUANTIDADE")
    private Double quantidade;

    @Column(name = "ULTIMA_ATUALIZACAO")
    private LocalDateTime ultimaAtualizacao;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "fk_produto", foreignKey = @ForeignKey(name = "fk_produto_estoque"), nullable = false)
    private Produto produto;

    public static Estoque of(Produto produto) {
        return Estoque.builder()
                .quantidade(0.0)
                .produto(produto)
                .build();
    }

}
