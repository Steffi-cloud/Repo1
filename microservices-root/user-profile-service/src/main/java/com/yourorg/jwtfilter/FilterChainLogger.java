package com.yourorg.jwtfilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
@Configuration
public class FilterChainLogger {

    private static final Logger logger = LoggerFactory.getLogger(FilterChainLogger.class);

    @Bean
    public CommandLineRunner logSecurityChains(ApplicationContext context) {
        return args -> {
            FilterChainProxy proxy = context.getBean(FilterChainProxy.class);
            logger.info("🛡️ Active Spring Security Filter Chains:");

            for (SecurityFilterChain chain : proxy.getFilterChains()) {
                if (chain instanceof DefaultSecurityFilterChain defaultChain) {
                    RequestMatcher matcher = defaultChain.getRequestMatcher();
                    logger.info("🔐 Matcher: {}", matcher);
                    defaultChain.getFilters().forEach(filter ->
                            logger.info("   ↳ Filter: {}", filter.getClass().getSimpleName()));
                } else {
                    logger.warn("⚠️ Unknown SecurityFilterChain implementation: {}", chain.getClass().getName());
                }
            }
        };
    }
}
