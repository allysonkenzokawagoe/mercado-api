package com.teste.project.modulos.autenticacao.service;

import com.teste.project.modulos.autenticacao.dto.UsuarioAutenticado;
import com.teste.project.modulos.autenticacao.util.TokenUtils;
import com.teste.project.modulos.comum.exceptions.UnauthorizedException;
import io.jsonwebtoken.io.Decoders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.*;

import java.security.Key;
import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class JwtService {

    @Value("${app-config.oauth.jwt-secret}")
    private String secret;

    public String gerarJwt(UsuarioAutenticado usuario) {
        return Jwts.builder()
                .setSubject(usuario.getUsuarioEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .claim("usuarioId", usuario.getUsuarioId())
                .claim("usuarioNome", usuario.getUsuarioNome())
                .claim("usuarioEmail", usuario.getUsuarioEmail())
                .claim("roles", usuario.getRoles())
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException ex) {
            return ex.getClaims();
        } catch (RuntimeException ex) {
            throw new UnauthorizedException("Usuário não autorizado");
        }
    }

    public Optional<Claims> getTokenData() {
        return Optional.ofNullable(TokenUtils.getHeaderToken())
                .map(token -> Jwts.parserBuilder()
                        .setSigningKey(getKey()).build()
                        .parseClaimsJws(token).getBody());
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
