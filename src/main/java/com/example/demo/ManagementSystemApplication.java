package com.example.demo;

import com.example.demo.model.Server;
import com.example.demo.enumeration.Status;
import com.example.demo.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementSystemApplication.class, args);
	}

	//  Run code after application is initialised.
	//  Note: servers are example servers, for testing. TODO: look up. Ping external.
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

}
