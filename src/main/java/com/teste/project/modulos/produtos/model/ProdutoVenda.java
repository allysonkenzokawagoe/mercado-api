package com.teste.project.modulos.produtos.model;

import com.teste.project.modulos.venda.model.Venda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ProdutoVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "QUANTIDADE")
    private Double quantidade;

    @Column(name = "SUBTOTAL")
    private Double subTotal;

    @Column(name = "DATA_VENDA")
    private LocalDateTime dataVenda;

    @ManyToOne
    @JoinColumn(name = "FK_PRODUTO_FILIAL", foreignKey = @ForeignKey(name = "FK_PRODUTO_FILIAL_PRODUTO_VENDIDO"), nullable = false)
    private ProdutoFilial produtoFilial;

    @ManyToOne
    @JoinColumn(name = "FK_VENDA", foreignKey = @ForeignKey(name = "FK_VENDA_PRODUTO_VENDIDO"), nullable = false)
    private Venda venda;

    public static ProdutoVenda of(ProdutoFilial produto, Venda venda, Double quantidade, Double valor) {
        return ProdutoVenda.builder()
                .produtoFilial(produto)
                .venda(venda)
                .quantidade(quantidade)
                .subTotal(valor)
                .dataVenda(LocalDateTime.now().withNano(0))
                .build();
    }

}
