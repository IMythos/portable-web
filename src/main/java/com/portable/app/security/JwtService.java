package com.portable.app.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;


@Service
public class JwtService {

    @Value("${app.jwt.key}")
    private String SECRET_KEY;

    private Algorithm geAlgorithm() {
        return Algorithm.HMAC256(SECRET_KEY);
    }

    public String generateToken(UserDetails userDetails) {
        String token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .sign(geAlgorithm());
        
        System.out.println("Token generado: " + token);         
        return token;
    }

    public String extractUsername(String token) {
        debugToken(token);
        return JWT.require(geAlgorithm())
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername());
        } catch (JWTVerificationException ex) {
            return false;
        }
    }  

    public void debugToken(String token) {
        try {
            var decodedJWT = JWT.decode(token);
            System.out.println("SUB: " + decodedJWT.getSubject());
            System.out.println("EXP: " + decodedJWT.getExpiresAt());
            System.out.println("ALG: " + decodedJWT.getAlgorithm());
        } catch (Exception ex) {
            System.out.println("Token inválido: " + ex.getMessage());
        }
    }
}
