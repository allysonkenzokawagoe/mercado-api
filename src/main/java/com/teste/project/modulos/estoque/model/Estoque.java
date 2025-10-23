package com.teste.project.modulos.estoque.model;

import com.teste.project.modulos.filiais.model.Filial;
import com.teste.project.modulos.produtos.model.Produto;
import com.teste.project.modulos.produtos.model.ProdutoFilial;
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

    @ManyToOne
    @JoinColumn(name = "FK_FILIAL", foreignKey = @ForeignKey(name = "FK_FILIAL_ESTOQUE"), nullable = false)
    private Filial filial;

    @OneToOne
    @JoinColumn(name = "FK_PRODUTO_FILIAL", foreignKey = @ForeignKey(name = "FK_PRODUTO_FILIAL_ESTOQUE"), nullable = false)
    private ProdutoFilial produtoFilial;

    public static Estoque of(ProdutoFilial produtoFilial, Filial filial) {
        return Estoque.builder()
                .quantidade(0.0)
                .filial(filial)
                .produtoFilial(produtoFilial)
                .build();
    }
}
