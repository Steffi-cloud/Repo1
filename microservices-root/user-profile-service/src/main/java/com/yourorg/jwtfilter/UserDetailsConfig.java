
package com.yourorg.jwtfilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDetailsConfig {
	  private static final Logger logger = LoggerFactory.getLogger(UserDetailsConfig.class);
	@Bean
	public UserDetailsService userDetailsService() {
		// Password encoder to encode plain text password "password"
		PasswordEncoder encoder = passwordEncoder();
		logger.debug("in usrDetailsService");
		UserDetails admin = User.builder().username("admin").password(encoder.encode("password")) // store encoded
																									// password
				.roles("ADMIN").build();

		UserDetails user = User.builder().username("user").password(encoder.encode("password")).roles("USER").build();
		logger.debug("in user {}",user.getUsername());
		return new InMemoryUserDetailsManager(admin, user);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
