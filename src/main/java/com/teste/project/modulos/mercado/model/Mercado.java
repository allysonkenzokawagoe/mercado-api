package com.teste.project.modulos.mercado.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.teste.project.modulos.filiais.model.Filial;
import com.teste.project.modulos.mercado.dto.MercadoRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "MERCADO")
public class Mercado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CNPJ")
    private String cnpj;

    public static Mercado of(MercadoRequest request) {
        return Mercado.builder()
                .nome(request.nome())
                .cnpj(request.cnpj())
                .build();
    }

}
