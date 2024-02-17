package com.example.demo;

import com.example.demo.enumeration.ClientStatus;
import com.example.demo.model.Client;
import com.example.demo.model.Server;
import com.example.demo.enumeration.Status;
import com.example.demo.repo.ClientRepo;
import com.example.demo.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Arrays;

@SpringBootApplication
public class ManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementSystemApplication.class, args);
	}

	//  Run code for management application after application is initialised.
	//  Note: servers are example servers, for testing.
	/*
	@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return args -> {
			serverRepo.save(new Server(null, "192.168.1.160", "ExampleServer1", "16 GB",
					"Personal PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.58", "ExampleServer2", "32 GB",
					"Web Server", "http://localhost:8080/server/image/server2.png", Status.SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.21", "ExampleServer3", "16 GB",
					"Personal PC", "http://localhost:8080/server/image/server3.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.14", "ExampleServer4", "8 GB",
					"Mail Server", "http://localhost:8080/server/image/server4.png", Status.SERVER_DOWN));
		};
	}
	*/

	//  Run code for client management application with examples.
	///*
	@Bean
	CommandLineRunner run(ClientRepo clientRepo){
		return args -> {
			clientRepo.save(new Client(null, "John", "Smith", "Google",
					"https://google.com", "johnsmith@gmail.com", "44", "7755666888", ClientStatus.ACTIVE));
			clientRepo.save(new Client(null, "Jane", "Jones", "Apple",
					"https://apple.com", "janejones@gmail.com", "44", "7755666888", ClientStatus.INACTIVE));
			clientRepo.save(new Client(null, "Jess", "Wilson", "IMB",
					"https://imb.com", "jesswilson@gmail.com", "44", "7755666888", ClientStatus.ACTIVE));
		};
	}
	//*/

	//  CorsError: backend must specify frontend can access it, else error as the two applications are on different domains.
	@Bean
	public CorsFilter corsFilter(){
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		//  Specify enabled domains: 3000 for React, 4200 for Angular.
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method",
				"Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
