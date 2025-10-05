package com.yourorg.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {
	  @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable()) // 💥 DISABLE CSRF FOR JWT-BASED AUTH
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/auth/**").permitAll() // allow auth-related endpoints
	                .anyRequest().authenticated()
	            );

	        return http.build();
	    }
}
