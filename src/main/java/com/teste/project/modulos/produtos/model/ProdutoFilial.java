package com.teste.project.modulos.produtos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teste.project.modulos.filiais.model.Filial;
import com.teste.project.modulos.produtos.enums.ESituacaoProduto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "PRODUTO_FILIAL")
public class ProdutoFilial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PRECO")
    private Double preco;

    @Column(name = "SITUACAO")
    @Enumerated(EnumType.STRING)
    private ESituacaoProduto situacao;

    @ManyToOne
    @JoinColumn(name = "FK_PRODUTO", foreignKey = @ForeignKey(name = "FK_PRODUTO_FILIAL_PRODUTO"), nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "FK_FILIAL", foreignKey = @ForeignKey(name = "FK_PRODUTO_FILIAL_FILIAL"), nullable = false)
    private Filial filial;

    public static ProdutoFilial of(Double preco) {
        return ProdutoFilial.builder()
                .preco(preco)
                .situacao(ESituacaoProduto.FORA_ESTOQUE)
                .build();
    }
}
