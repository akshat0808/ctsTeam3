package com.cts.project.movieZuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableDiscoveryClient // It acts as a eureka client
@EnableZuulProxy      // Enable Zuul
@SpringBootApplication
public class MovieZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieZuulGatewayApplication.class, args);
	}

}
