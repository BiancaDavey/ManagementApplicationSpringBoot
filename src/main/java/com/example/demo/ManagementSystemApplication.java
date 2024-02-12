package com.example.demo;

import com.example.demo.enumeration.SiteStatus;
import com.example.demo.model.Server;
import com.example.demo.enumeration.Status;
import com.example.demo.model.Site;
import com.example.demo.repo.ServerRepo;
import com.example.demo.repo.SiteRepo;
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

	//  Run code for servers after application is initialised.
	//  Note: servers are example servers, for testing.
	///*
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
	//*/

	//  TODO: Site manager if using.
	/*
	@Bean
	CommandLineRunner run(SiteRepo siteRepo){
		return args -> {
			siteRepo.save(new Site(null, "https://google.com", "ExampleSite1Google",
					"Main", "http://localhost:8080/site/image/site1.png", SiteStatus.SITE_UP));
			siteRepo.save(new Site(null, "https://yahoo.com", "ExampleSite2Yahoo",
					"Main", "http://localhost:8080/site/image/site2.png", SiteStatus.SITE_DOWN));
			siteRepo.save(new Site(null, "https://www.nationalgeographic.com/", "ExampleSite3NationalGeographic",
					"Interest", "http://localhost:8080/site/image/site3.png", SiteStatus.SITE_UP));
			siteRepo.save(new Site(null, "https://biancamdaveyportfolio.wordpress.com/", "ExampleSite4Wordpress",
					"Portfolio", "http://localhost:8080/site/image/site4.png", SiteStatus.SITE_DOWN));
		};
	}
	*/

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
