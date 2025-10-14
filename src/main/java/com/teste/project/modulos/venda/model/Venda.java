package com.teste.project.modulos.venda.model;

import com.teste.project.modulos.produtos.model.ProdutoVenda;
import com.teste.project.modulos.venda.enums.ETipoPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VENDA")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "VALOR")
    private Double valor;

    @Column(name = "DATA_VENDA")
    private LocalDateTime dataVenda;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_PAGAMENTO")
    private ETipoPagamento tipoPagamento;

    @OneToMany
    private List<ProdutoVenda> produtoVendidos;

    public static Venda of() {
        return Venda.builder()
                .valor(0.0)
                .build();
    }
}
