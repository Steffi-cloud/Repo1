package com.yourorg.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourorg.auth.dto.RegisterRequest;
import com.yourorg.jwt.JwtUtil;
@RestController
@RequestMapping("/auth")
public class AuthController {
	  @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
	        String username = request.get("username");
	        String password = request.get("password");

	        // Hardcoded user validation
	        if ("admin".equals(username) && "password".equals(password)) {
	            String token = JwtUtil.generateToken(username);
	            return ResponseEntity.ok(Map.of("token", token));
	        } else {
	            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
	        }
	    }

	    @PostMapping("/register")
	    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
	        // Dummy logic for registration
	        return ResponseEntity.ok("User registered: " + request.getUsername());
	    }
	    
	    @PostMapping("/logout")
	    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
	        // In a stateless system, we don’t "invalidate" the JWT unless we use a blacklist.
	        // Here, we just respond that logout is successful.

	        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;

	        // Optional: validate token if you want to reject invalid logouts
	        boolean isValid = JwtUtil.validateToken(token);
	        if (!isValid) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid or expired token"));
	        }

	        // Acknowledge logout (client should delete token locally)
	        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
	    }

	    @GetMapping("/validate")
	    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
	        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;

	        boolean isValid = JwtUtil.validateToken(token);

	        Map<String, Object> response = new HashMap<>();
	        response.put("valid", isValid);

	        if (isValid) {
	            return ResponseEntity.ok(response);
	        } else {
	            response.put("error", "Invalid or expired token");
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	        }
	    }

}
