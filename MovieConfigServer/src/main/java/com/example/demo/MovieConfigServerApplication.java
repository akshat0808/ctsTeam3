package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
@EnableConfigServer
@SpringBootApplication
public class MovieConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieConfigServerApplication.class, args);
	}

}
