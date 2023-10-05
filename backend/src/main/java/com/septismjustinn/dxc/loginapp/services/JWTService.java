package com.septismjustinn.dxc.loginapp.services;

import com.septismjustinn.dxc.loginapp.data.LoginRepository;
import com.septismjustinn.dxc.loginapp.models.Login;
import com.septismjustinn.dxc.loginapp.models.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class JWTService {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; //24 hours
    private final LoginRepository loginRepo;

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    public JWTService(LoginRepository loginRepo) {
        this.loginRepo = loginRepo;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(UUID jti, User user) {
        return Jwts.builder()
                .setId(jti.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .setSubject(user.getUsername())
                .claim("name", user.getName())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Retrieve claims from JWT
    public Claims extractClaim(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Token verification
    public Optional<UserDetails> isTokenValid(String token) {
        Claims tokenUser = extractClaim(token);
        Optional<Login> existingLogin = loginRepo.findById(UUID.fromString(tokenUser.getId()));
        if (existingLogin.isEmpty()) {
            return Optional.empty();
        } else {
            User user = existingLogin.get().getUser();
            if (tokenUser.getSubject().equals(user.getUsername()) &&
                            tokenUser.getExpiration().after(new Date())) {
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        }
    }
}
