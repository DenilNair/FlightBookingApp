package com.denil.GatewayService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	/*
	@Value("flightlist.service.uri")
	String flightlistServiceUri;
	
	@Value("auth.service.uri")
	String authServiceUri;
	
	@Value("aircraft.service.uri")
	String aircraftServiceUri;
	
	@Value("customer.service.uri")
	String customerServiceUri;
	*/

	@Bean
	public RouteLocator myROutes(RouteLocatorBuilder rb) {
		return rb.routes().route(p->p
				.path("")
				.uri("http://localhost:8080"))
				
				
				
				.route(p->p
				.path("/customer/**")
				//.filters(f->f.circuitBreaker(c->c.setName("denCB").setFallbackUri("/default-fallback")))
				//.filters(f->f.circuitBreaker(s->s.setFallbackUri("/default-fallback")))
				.uri("lb://customer-service"))
				
				
				.route(p->p
				.path("/aircraft/**")
				.uri("lb://AIRCRAFT-SERVICE"))
				
				.route(p->p
				.path("/auth/**")
				.uri("lb://security-service"))
				
				
				.route(p->p
						.path("/flightservice/**")
						.uri("lb://FLIGHT-LISTING"))
				
				.build();
	}
	
	
	
	
	
}
