package com.denil.aircraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AircraftApplication {

	public static void main(String[] args) {
		SpringApplication.run(AircraftApplication.class, args);
	}

}
