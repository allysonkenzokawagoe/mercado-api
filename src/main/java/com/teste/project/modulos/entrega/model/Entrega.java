package com.teste.project.modulos.entrega.model;

import com.teste.project.modulos.endereco.model.Endereco;
import com.teste.project.modulos.entrega.enums.ESituacaoEntrega;
import com.teste.project.modulos.produtos.dto.VendaDto;
import com.teste.project.modulos.venda.model.Venda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ENTREGA")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CLIENTE_NOME")
    private String clienteNome;

    @Column(name = "SITUACAO")
    @Enumerated(EnumType.STRING)
    private ESituacaoEntrega situacao;

    @ManyToOne
    @JoinColumn(name = "FK_ENDERECO", foreignKey = @ForeignKey(name = "FK_ENDERECO_ENTREGA"), nullable = false)
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "FK_VENDA", foreignKey = @ForeignKey(name = "FK_VENDA_ENTREGA"), nullable = false)
    private Venda venda;

    public static Entrega of(VendaDto venda) {
        return Entrega.builder()
                .clienteNome(venda.nomeCliente())
                .endereco(venda.endereco())
                .situacao(ESituacaoEntrega.ENTREGA_ANDAMENTO)
                .build();
    }

}
