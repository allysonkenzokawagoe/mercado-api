package com.teste.project.modulos.filiais.model;

import com.teste.project.modulos.endereco.model.Endereco;
import com.teste.project.modulos.mercado.model.Mercado;
import com.teste.project.modulos.user.model.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"endereco", "usuarios"})
@Entity
@Table(name = "FILIAL")
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "FK_MERCADO", foreignKey = @ForeignKey(name = "FK_MERCADO_FILIAL"), nullable = false)
    private Mercado mercado;

    @OneToMany(mappedBy = "filial")
    private List<Usuario> usuarios;

    public static Filial of(Endereco endereco, Mercado mercado) {
        return Filial.builder()
                .endereco(endereco)
                .mercado(mercado)
                .build();
    }
}
