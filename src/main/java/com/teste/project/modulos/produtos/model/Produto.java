package com.teste.project.modulos.produtos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teste.project.modulos.categoria.model.Categoria;
import com.teste.project.modulos.produtos.dto.ProdutoRequest;
import com.teste.project.modulos.produtos.enums.ETipoMedida;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "MARCA")
    private String marca;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "TIPO_MEDIDA")
    @Enumerated(EnumType.STRING)
    private ETipoMedida tipoMedida;

    @JoinColumn(name = "FK_CATEGORIA", foreignKey = @ForeignKey(name = "FK_CATEGORIA_USUARIO"),nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    public static Produto of(ProdutoRequest request) {
        return Produto.builder()
                .nome(request.nome())
                .marca(request.marca())
                .descricao(request.descricao())
                .tipoMedida(request.tipoMedida())
                .build();
    }
}
