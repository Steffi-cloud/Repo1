package com.yourorg.jwt;

import java.security.Key;
import java.util.Date;


import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {
	 private static final Key SECRET_KEY = Keys.hmacShaKeyFor("my-super-secret-key-1234567890123456".getBytes());
	    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

	    public static String generateToken(String username) {
	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
	                .compact();
	    }

	    public static boolean validateToken(String token) {
	        try {
	            Jwts.parserBuilder()
	                .setSigningKey(SECRET_KEY)
	                .build()
	                .parseClaimsJws(token);
	            return true;
	        } catch (JwtException e) {
	            // Optionally log or rethrow
	            return false;
	        }
	    }

	    public static String extractUsername(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(SECRET_KEY)
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	    }
	    
	    
}
