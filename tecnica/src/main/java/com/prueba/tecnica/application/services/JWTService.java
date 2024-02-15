package com.prueba.tecnica.application.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.domain.models.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Service
public class JWTService {

    @Value("3600000")
    private long expirationMs;

    private String secret = "2ES2nH4J/hJf18sSLTh/X2JvXtOUo7NYX4irfCOTK+MWAz0S7xOzSF/KbhZRxE17e208EQaxAsXQkmG2YgxNmQ==";

    public String generateToken(UserModel user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("jsonPayload", user);
        Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
        return Jwts.builder().setClaims(claims).setSubject(user.getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Claims decodeToken(String jwtToken) {
        Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public boolean validateToken(String jwtToken) {
        try {
            Claims claims = this.decodeToken(jwtToken);
            System.out.println(claims.get("jsonPayload"));
            // Accede a los claims según sea necesario
            System.out.println("Subject: " + claims.getSubject());
            System.out.println("Issued At: " + claims.getIssuedAt());
            System.out.println("Expiration: " + claims.getExpiration());
            return true;
            // Puedes acceder a otros claims según sea necesario
        } catch (io.jsonwebtoken.security.SignatureException e) {
            System.err.println("Error de firma del token: " + e.getMessage());
            return false;
        }
    }
}
