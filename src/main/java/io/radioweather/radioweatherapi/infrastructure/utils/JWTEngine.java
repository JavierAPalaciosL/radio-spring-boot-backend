package io.radioweather.radioweatherapi.infrastructure.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JWTEngine {

    private static String secretKey = "mi_clave_secreta#q12412412122312312312d@1p2oiejojnwrjpo21nrp12ojkrp21nrjop31nfop31ojfmp3o1kjf2pnifepoqkfpiqweof";

    private JWTEngine(){}

    public static String generateNewJWT(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
    }

    public static Claims validateToken(String jwtToken) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .build();
        Claims claims = parser.parseClaimsJws(jwtToken).getBody();
        return claims;
    }




}
