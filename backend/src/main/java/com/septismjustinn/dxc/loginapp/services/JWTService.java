package com.septismjustinn.dxc.loginapp.services;

import com.septismjustinn.dxc.loginapp.models.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Service
public class JWTService {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; //24 hours

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    public JWTService() {
    }

    private SecretKey getSignKey() {
        //4/10/2023 some problem with JJWT library signing JWTs with HS256 keys
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(UUID jti, User user) {
        SecretKey key = Jwts.SIG.HS256.key().build(); //or HS384.key() or HS512.key()
        return Jwts.builder()
                .id(jti.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .subject(user.getUsername())
                .claim("name", user.getName())
                .signWith(getSignKey())
                .compact();
    }

    // Retrieve claims from JWT
    private Claims extractClaim(String token) {
        return Jwts.parser()
                .decryptWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
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
