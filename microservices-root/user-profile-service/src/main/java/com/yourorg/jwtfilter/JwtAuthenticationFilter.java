package com.yourorg.jwtfilter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	   private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	  private final UserDetailsService userDetailsService;

	    public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
	        this.userDetailsService = userDetailsService;
	    }

	    @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain filterChain)
	            throws ServletException, IOException {

	        final String authHeader = request.getHeader("Authorization");
	        logger.debug("Authorization header: {}", authHeader);

	        String username = null;
	        String token = null;

	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            token = authHeader.substring(7);
	            try {
	                logger.debug("Trying to extract username from token: {}", token);
	                username = JwtUtil.extractUsername(token);
	            } catch (Exception e) {
	                logger.error("Failed to parse JWT token", e);
	            }

	        }

	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            // load user details from some UserDetailsService or stub
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	            logger.debug("the userDetails  is {}",userDetails.getUsername());
	            logger.debug("the userDetails  is {}",userDetails.getPassword());
	            if (JwtUtil.validateToken(token)) {
	            	 logger.debug("JWT token validated successfully");
	                UsernamePasswordAuthenticationToken authToken =
	                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                logger.debug("the authtoken is {}",authToken);
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	            }
	            else {
	                logger.warn("JWT token validation failed");
	            }
	        }

	        filterChain.doFilter(request, response);
}
}