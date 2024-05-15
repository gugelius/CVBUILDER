package com.example.testproject.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class TokenUtils {
        public static String createJWT(String id, String issuer, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        Date now = new Date(nowMillis);
        String secretKey = "c3VwZXIgcHVwZXIgZHVwZXIgbWVnYSB1bHRyYSBzZWNyZXQgZ2lnYSBrZXk=";
        byte[] apiKeySecretBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .setExpiration(exp) // Устанавливаем время истечения
                .signWith(signatureAlgorithm, signingKey)
                .compact();
        }
        public static String decodeJWT(String jwt) {
                String secretKey = "c3VwZXIgcHVwZXIgZHVwZXIgbWVnYSB1bHRyYSBzZWNyZXQgZ2lnYSBrZXk=";
                byte[] apiKeySecretBytes = secretKey.getBytes(StandardCharsets.UTF_8);
                Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());

                Jws<Claims> claims = Jwts.parserBuilder()
                        .setSigningKey(signingKey)
                        .build()
                        .parseClaimsJws(jwt);

                return claims.getBody().getId();
        }
        public static boolean validateJWT(String jwt) {
                String secretKey = "c3VwZXIgcHVwZXIgZHVwZXIgbWVnYSB1bHRyYSBzZWNyZXQgZ2lnYSBrZXk=";
                byte[] apiKeySecretBytes = secretKey.getBytes(StandardCharsets.UTF_8);
                Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());

                try {
                        Jws<Claims> claims = Jwts.parserBuilder()
                                .setSigningKey(signingKey)
                                .build()
                                .parseClaimsJws(jwt);

                        if (claims.getBody().getExpiration().before(new Date())) {
                                return false;
                        }

                        return true;
                } catch (Exception e) {
                        return false;
                }
        }

}
