// src/main/java/com/example/backend_prueba/config/SecurityConfig.java
package com.example.backend_prueba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // Utilizar la configuración de CORS global
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register").permitAll() // Permitir acceso sin autenticación
                        .anyRequest().authenticated() // Otros endpoints requieren autenticación
                )
                .httpBasic(Customizer.withDefaults()); // Autenticación básica

        return http.build();
    }
}
