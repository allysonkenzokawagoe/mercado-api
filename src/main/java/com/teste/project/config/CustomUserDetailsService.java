package com.teste.project.config;

import com.teste.project.modulos.user.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .map(user -> new User(
                        user.getId() + "-" + user.getNome(),
                        user.getPassword(),
                        user.getAuthorities()
                        )
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
