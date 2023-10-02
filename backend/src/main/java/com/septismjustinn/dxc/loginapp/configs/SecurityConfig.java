package com.septismjustinn.dxc.loginapp.configs;

import com.septismjustinn.dxc.loginapp.models.Role;
import com.septismjustinn.dxc.loginapp.services.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    AppUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                (authz) -> authz
                        .requestMatchers(HttpMethod.POST, "/public/register").permitAll()
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/protected/admin").hasRole(Role.ADMIN.toString())
                        .requestMatchers("/protected/**").authenticated()
                )
                .csrf((csrf) -> csrf.disable())
                .build();
        // Disable CSRF since authentication will be done via Tokens for protected endpoints
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return new ProviderManager(authProvider);
    }
}
