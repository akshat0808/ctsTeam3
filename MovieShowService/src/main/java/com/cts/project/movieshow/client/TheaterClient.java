package com.cts.project.movieshow.client;

import java.util.Optional;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.project.movieshow.entity.Theater;

@FeignClient(name = "theater-service")
public interface TheaterClient 
{
	@LoadBalanced
	@GetMapping("/gettheater/theaterid/{id}")
	public Optional<Theater> getTheaterById(@PathVariable int theaterid);
}
