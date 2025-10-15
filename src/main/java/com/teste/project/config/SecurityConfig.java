package com.teste.project.config;

import com.teste.project.modulos.autenticacao.filter.AuthTokenFilter;
import com.teste.project.modulos.autenticacao.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.spec.SecretKeySpec;

@RequiredArgsConstructor(onConstructor_ = @Lazy)
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app-config.oauth.jwt-secret}")
    private String secretKey;

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
                        .requestMatchers("/oauth", "/oauth/**").permitAll()
                        .requestMatchers("/api/usuario", "/api/usuario/**").permitAll()
                        .requestMatchers("/api/cargo", "/api/cargo/**").permitAll()
                        .requestMatchers("/api/endereco", "/api/endereco/**").permitAll()
                        .requestMatchers("/api/mercado", "/api/mercado/**").permitAll()
                        .requestMatchers("/api/filial",  "/api/filial/**").permitAll()
                        .requestMatchers("/api/categoria", "/api/categoria/**").hasAnyRole("ADMIN")
                        .requestMatchers("/api/produto", "/api/produto/**").permitAll()
                        .requestMatchers("/api/estoque", "/api/estoque/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(FormLoginConfigurer::disable)
                .sessionManagement(ss -> ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoAuthenticationProvider())
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(
                new SecretKeySpec(secretKey.getBytes(), "HmacSHA256")
        ).build();
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
