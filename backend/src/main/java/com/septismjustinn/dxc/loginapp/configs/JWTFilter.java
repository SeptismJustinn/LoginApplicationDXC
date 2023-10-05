package com.septismjustinn.dxc.loginapp.configs;

import com.septismjustinn.dxc.loginapp.services.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;

    public JWTFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest req,
                                    @NonNull HttpServletResponse res,
                                    @NonNull FilterChain filterChain)
        throws ServletException, IOException {
        String authHeader = req.getHeader("Authorization");
        if (authHeader == null || authHeader == "" ||
                !authHeader.startsWith("Bearer ") || authHeader.equals("Bearer undefined")) {
            // If no Bearer, do nothing and proceed with chain.
            // System.out.println("No access token");
        } else {
            String jwt = authHeader.substring(7);
            Optional<UserDetails> validJwt = jwtService.isTokenValid(jwt);
            if (validJwt.isPresent()) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    validJwt.get(), null, validJwt.get().getAuthorities()
                );
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(req, res);
    }
}
