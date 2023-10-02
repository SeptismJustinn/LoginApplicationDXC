package com.septismjustinn.dxc.loginapp.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                (authz) -> authz
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/protected/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("Admin")
                )
                .build();
    }
}
