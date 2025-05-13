package com.example.ticket.management.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JWTUtil {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    private final long EXPIRATION_TIME = 1000*60*60;
    private final String key = "this-is-a-very-long-and-easy-to-remember-secret-key-but-please-do-not-use-it-in-production-it-is-not-secure-enough";
    private final SecretKey finalKey= Keys.hmacShaKeyFor(key.getBytes());

    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username); // Load user details
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        claims.put("roles", authorities);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
                .signWith(finalKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserName(String token){
        Claims body = extractClaims(token);

        return body.getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(finalKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String username, UserDetails userDetails, String token) {
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        Claims claims = extractClaims(token);
        return claims.getExpiration(); // Get the 'exp' claim as a Date
    }



}
