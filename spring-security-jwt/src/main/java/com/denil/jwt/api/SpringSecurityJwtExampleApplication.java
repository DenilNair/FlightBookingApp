package com.denil.jwt.api;

import com.denil.jwt.api.entity.User;
import com.denil.jwt.api.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication

public class SpringSecurityJwtExampleApplication {
    @Autowired
    private UserRepository repository;

    /*
    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(101, "javatechie", "password", "javatechie@gmail.com","ADMIN"),
                new User(102, "user1", "pwd1", "user1@gmail.com","USER"),
                new User(103, "user2", "pwd2", "user2@gmail.com","USER"),
                new User(104, "user3", "pwd3", "user3@gmail.com","ADMIN")
        ).collect(Collectors.toList());
        repository.saveAll(users);
    }*/

    public static void main(String[] args) {
    	System.out.println("CORS");
        SpringApplication.run(SpringSecurityJwtExampleApplication.class, args);
    }
    /*
    @Bean
	public WebMvcConfigurer getCorsConfiguration() {
		return new WebMvcConfigurer() {
		
		
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			// TODO Auto-generated method stub
			//WebMvcConfigurer.super.addCorsMappings(registry);
			registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
		}
		};
	}*/
    

}
