package com.teste.project.modulos.endereco.model;

import com.teste.project.modulos.endereco.dto.CepResponse;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
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

    public static Endereco of(CepResponse response, Integer numero) {
        return Endereco.builder()
                .cep(response.cep())
                .logradouro(response.logradouro())
                .estado(response.estado())
                .localidade(response.localidade())
                .uf(response.uf())
                .numero(numero)
                .estado(response.estado())
                .build();
    }
}
