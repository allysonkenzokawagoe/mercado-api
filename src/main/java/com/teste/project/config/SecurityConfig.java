package com.teste.project.config;

import com.teste.project.modulos.autenticacao.filter.AuthTokenFilter;
import com.teste.project.modulos.autenticacao.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/usuario", "/api/usuario/**").permitAll()
                        .requestMatchers("/api/cargo", "api/cargo/**").permitAll()
                        .requestMatchers("/api/endereco", "/api/endereco/**").permitAll()
                        .requestMatchers("/api/mercado", "/api/mercado/**").permitAll()
                        .requestMatchers("/api/filial",  "/api/filial/**").permitAll()
                        .requestMatchers("/api/categoria", "/api/categoria/**").permitAll()
                        .requestMatchers("/api/produto", "/api/produto/**").permitAll()
                        .requestMatchers("/api/estoque", "/api/estoque/**").permitAll()
                        .anyRequest().authenticated())
                .authenticationProvider(daoAuthenticationProvider())
                .build();
    }

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter(userDetailsService, jwtService);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        var authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
