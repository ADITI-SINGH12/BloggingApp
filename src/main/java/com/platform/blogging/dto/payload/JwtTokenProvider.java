package com.platform.blogging.dto.payload;

import io.jsonwebtoken.*;
import com.fasterxml.jackson.core.ErrorReportConfiguration;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Component
public class JwtTokenProvider {
    // Secret key to sign the JWT. You can inject it from properties or environment variables.
//    @Value("${jwt.secret}")
//    private String secretKey;
    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // Token expiration time in milliseconds (e.g., 1 hour)
    @Value("${jwt.expiration}")
    private long expirationTime;
    /**
     * Generate a JWT token based on the username.
     * @param username the username to embed in the token
     * @return the generated JWT token
     */
    public String generateToken(String username) {
        // Set the JWT Claims (payload)
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date()) // Issue time
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Expiration time
                .signWith(key) // Signing the JWT with secret key
                .compact();
    }
    /**
     * Validate the JWT token
     * @param token the JWT token to validate
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String token) {
        try {
            // Parse and validate the JWT token
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true; // Token is valid
        } catch (JwtException | IllegalArgumentException e) {
            // Token is either expired or invalid
            return false;
        }
    }

    /**
     * Get the username (subject) from the JWT token
     * @param token the JWT token
     * @return the username (subject) embedded in the token
     */
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
