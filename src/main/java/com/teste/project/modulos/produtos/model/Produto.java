package com.teste.project.modulos.produtos.model;

import com.teste.project.modulos.categoria.model.Categoria;
import com.teste.project.modulos.estoque.model.Estoque;
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

    @Column(name = "PRECO")
    private Double preco;

    @Column(name = "TIPO_MEDIDA")
    @Enumerated(EnumType.STRING)
    private ETipoMedida tipoMedida;

    @JoinColumn(name = "FK_CATEGORIA", foreignKey = @ForeignKey(name = "FK_CATEGORIA_USUARIO"),nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    public static Produto of(ProdutoRequest request) {
        return Produto.builder()
                .nome(request.nome())
                .descricao(request.descricao())
                .tipoMedida(request.tipoMedida())
                .build();
    }

}
