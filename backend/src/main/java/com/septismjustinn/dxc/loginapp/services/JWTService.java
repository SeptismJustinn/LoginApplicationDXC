package com.septismjustinn.dxc.loginapp.services;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; //24 hours

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;


    public String generateAccessToken(UserDetails user) {
        return generateAccessToken(new HashMap<String, Object>(), user);
    }
    public String generateAccessToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(String.format(user.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // Retrieve claims from JWT
    private Claims extractClaim(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // Token verification
    public boolean isTokenValid(String token, UserDetails user) {
        Claims tokenUser = extractClaim(token);
        return (
                tokenUser.getSubject().equals(user.getUsername()) &&
                        tokenUser.getExpiration().before(new Date())
                );
    }
}
