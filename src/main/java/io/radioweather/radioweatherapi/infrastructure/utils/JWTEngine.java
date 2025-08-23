package io.radioweather.radioweatherapi.infrastructure.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTEngine {

    @Value("${spring.JWT.secret}")
    private String secretKey;

    private static String SECRET_KEY;

    @PostConstruct
    public void init() {
        SECRET_KEY = secretKey;
    }

    private JWTEngine(){}

    public static String generateNewJWT(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
                .compact();
    }

    public static Claims validateToken(String jwtToken) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .build();
        Claims claims = parser.parseClaimsJws(jwtToken).getBody();
        return claims;
    }




}
