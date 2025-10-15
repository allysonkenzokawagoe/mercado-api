package com.teste.project.modulos.autenticacao.service;

import com.teste.project.modulos.autenticacao.dto.UsuarioAutenticado;
import com.teste.project.modulos.comum.exceptions.NotFoundException;
import com.teste.project.modulos.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class AutenticacaoService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public UsuarioAutenticado autenticarUsuario(String username, String password) {
        System.out.println("a");
        var authToken = new UsernamePasswordAuthenticationToken(username, password);
        System.out.println("b");
        var authetication = authenticationManager.authenticate(authToken);
        System.out.println("c");
        SecurityContextHolder.getContext().setAuthentication(authetication);

        System.out.println(jwtService.getTokenData());
        var usuario = getUsuarioAutenticado();
        var jwt = jwtService.gerarJwt(usuario);
        usuario.setToken(jwt);
        return usuario;
    }

    public UsuarioAutenticado getUsuarioAutenticado() {
        if(hasAuthentication()) {
            System.out.println(getUsuarioId());
            System.out.println(userRepository.findById(getUsuarioId()));
            return jwtService.getTokenData()
                    .map(UsuarioAutenticado::new)
                    .orElseGet(() -> UsuarioAutenticado.of(
                            userRepository.findById(getUsuarioId()).orElseThrow(() -> new NotFoundException("Usuário não encontrado"))
                    ));
        }
        return null;
    }

    public String getUsuarioId() {
        var pt1 =  SecurityContextHolder.getContext().getAuthentication().getName().split(Pattern.quote("-"))[0];
        var pt2 =  SecurityContextHolder.getContext().getAuthentication().getName().split(Pattern.quote("-"))[1];
        var pt3 =  SecurityContextHolder.getContext().getAuthentication().getName().split(Pattern.quote("-"))[2];

        return pt1+"-"+pt2+"-"+pt3;
    }

    private static boolean hasAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

}
