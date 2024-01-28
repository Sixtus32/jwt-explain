package com.s6x.jwtexplain.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.s6x.jwtexplain.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration // clase con metodos que van a estar marcado con la anotación @Bean para la configuración.
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authProvider;
    /**
     * METODOS PARA RESTRIGUIR ACCESOS EN FUNCIÓN DE "ROLES" EJ.
     * */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
            ).sessionManagement(sessionManeger -> 
            		sessionManeger
            		.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            
            //.formLogin(); // Agrega la configuración de inicio de sesión por formulario

        return http.build();
    }
}
