package com.teste.project.modulos.autenticacao.service;

import com.teste.project.modulos.autenticacao.dto.UsuarioAutenticado;
import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AutenticacaoService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public UsuarioAutenticado autenticarUsuario(String username, String password) throws Exception {
        var authToken = new UsernamePasswordAuthenticationToken(username, password);
        var authetication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authetication);

        var usuario = getUsuarioAutenticado();
        var jwt = jwtService.gerarJwt(usuario);
        usuario.setToken(jwt);
        return usuario;
    }

    public UsuarioAutenticado getUsuarioAutenticado() {
        if(hasAuthentication()) {
            return jwtService.getTokenData()
                    .map(UsuarioAutenticado::new)
                    .orElseGet(() -> UsuarioAutenticado.of(
                            userRepository.findById(getUsuarioId()).orElseThrow(() -> new NotFoundException("Usuário não encontrado"))
                    ));
        }
        return null;
    }

    public String getUsuarioId() {
        return SecurityContextHolder.getContext().getAuthentication().getName().split("-")[0];
    }

    private static boolean hasAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

}
