package com.techtracers.lockitemapi.security.middleware;

import io.jsonwebtoken.*;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtHandler {
    @Value("${authorization.jwt.secret}")
    private String secret;

    @Value("${authorization.jwt.expiration.days}")
    private int expirationDays;

    public String generateToken(Authentication authentication) {
        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
        String subject = userDetails.getId()
                .toString();

        Date issuedAt = new Date();

        Date expiration = DateUtils.addDays(issuedAt, expirationDays);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Long getIdFromToken(String token) {
        return Long.parseLong(
                Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject()
        );
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.printf("Invalid JSON Web Token Signature: %s%n", e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.printf("Invalid JSON Web Token: %s%n", e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.printf("JSON Web Token is expired: %s%n", e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.printf("JSON Web Token is unsupported: %s%n", e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.printf("JSON Web Token claims string is empty: %s%n", e.getMessage());
        }
        return false;

    }
}
