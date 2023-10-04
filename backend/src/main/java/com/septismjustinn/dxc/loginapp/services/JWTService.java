package com.septismjustinn.dxc.loginapp.services;

import com.septismjustinn.dxc.loginapp.models.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class JWTService {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; //24 hours

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    public JWTService() {
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
//    private Claims extractClaim(String token) {
//        return Jwts.parser()
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
//
//    // Token verification
//    public boolean isTokenValid(String token, UserDetails user) {
//        Claims tokenUser = extractClaim(token);
//
//        return (
//                tokenUser.getSubject().equals(user.getUsername()) &&
//                        tokenUser.getExpiration().before(new Date())
//                );
//    }
}
