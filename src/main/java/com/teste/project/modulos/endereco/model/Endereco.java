package com.teste.project.modulos.endereco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teste.project.modulos.endereco.dto.CepResponse;
import com.teste.project.modulos.filiais.model.Filial;
import com.teste.project.modulos.user.model.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@ToString(exclude = {"usuario", "filial"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "NUMERO")
    private Integer numero;

    @Column(name = "LOCALIDADE")
    private String localidade;

    @Column(name = "UF")
    private String uf;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "CEP")
    private String cep;

    @OneToOne(mappedBy = "endereco")
    @JsonIgnore
    private Filial filial;

    @OneToOne(mappedBy = "endereco")
    @JsonIgnore
    private Usuario usuario;

    public static Endereco of(CepResponse response, Integer numero) {
        return Endereco.builder()
                .cep(response.cep())
                .logradouro(response.logradouro())
                .estado(response.estado())
                .localidade(response.localidade())
                .numero(numero)
                .estado(response.estado())
                .build();
    }
}
