package com.teste.project.modulos.user.model;

import com.teste.project.modulos.cargo.model.Cargo;
import com.teste.project.modulos.comum.utils.IdUtils;
import com.teste.project.modulos.endereco.model.Endereco;
import com.teste.project.modulos.filiais.model.Filial;
import com.teste.project.modulos.user.dto.UserRequest;
import com.teste.project.modulos.user.enums.ESituacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "USUARIO")
public class Usuario implements UserDetails {

    @Id
    private String id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "CPF")
    private Integer cpf;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "SALARIO")
    private Double salario;

    @Column(name = "SITUACAO")
    private ESituacao situacao;

    @OneToOne
    @JoinColumn(name = "FK_ENDERECO")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "FK_CARGO", foreignKey = @ForeignKey(name = "FK_CARGO_USER"), nullable = false)
    private Cargo cargo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_FILIAL", foreignKey = @ForeignKey(name = "FK_FILIAL_USUARIO"), nullable = false)
    private Filial filial;

    public static Usuario of(UserRequest request) {
        return Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .cpf(request.cpf())
                .situacao(ESituacao.ATIVO)
                .salario(request.salario())
                .build();
    }

    @PrePersist
    public void generateId() {
        this.id = Optional.ofNullable(this.id)
                .orElse(IdUtils.generateId());
    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(cargo.getPermissao().getRoleName()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
