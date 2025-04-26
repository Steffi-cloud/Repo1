package com.example.demo.config;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
//import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions.CircuitBreakerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//wrong imports since circuitbreaker is already in spring's dependency so 
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
@Configuration
public class ResilienceConfig {
	@Bean
	  public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
	      return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
	          .timeLimiterConfig(TimeLimiterConfig.custom()
	              .timeoutDuration(Duration.ofMillis(2000))
	              .build())
	            .circuitBreakerConfig(CircuitBreakerConfig.custom()
	                    .slidingWindowSize(10)
	                    .slidingWindowType(SlidingWindowType.COUNT_BASED)
	                    .permittedNumberOfCallsInHalfOpenState(3)
	                    .failureRateThreshold(50.0F)
	                    .waitDurationInOpenState(Duration.ofSeconds(5))
	                    .slowCallDurationThreshold(Duration.ofMillis(200))
	                    .slowCallRateThreshold(50.0F)
	                    .automaticTransitionFromOpenToHalfOpenEnabled(true)
	                    .minimumNumberOfCalls(5)
	                    .build())
	          .build());
	  }
}
