package com.teste.project.modulos.autenticacao.dto;

import com.teste.project.modulos.comum.enums.EPermissao;
import com.teste.project.modulos.user.model.Usuario;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class UsuarioAutenticado {

    private String token;
    private String usuarioId;
    private String usuarioNome;
    private String usuarioEmail;
    private EPermissao permissao;
    private List<String> roles;

    public UsuarioAutenticado(Claims claims) {
        this.usuarioId = claims.get("usuarioId", String.class);
        this.usuarioNome = claims.get("usuarioNome", String.class);
        this.usuarioEmail = claims.get("usuarioEmail", String.class);
        this.permissao = claims.get("permissao", EPermissao.class);
        this.roles = (List<String>) claims.get("roles", List.class);
    }

    public static UsuarioAutenticado of(Usuario usuario) {
        return UsuarioAutenticado.builder()
                .usuarioId(usuario.getId())
                .usuarioNome(usuario.getNome())
                .usuarioEmail(usuario.getEmail())
                .permissao(usuario.getCargo().getPermissoes().getFirst())
                .roles(usuario.getCargo().getPermissoes().stream().map(EPermissao::getRole).collect(Collectors.toList()))
                .build();
    }

    public boolean isAdmin() {
        return this.permissao.equals(EPermissao.ADMIN);
    }
}
