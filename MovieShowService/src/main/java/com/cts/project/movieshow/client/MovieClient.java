package com.cts.project.movieshow.client;

import java.util.Optional;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.project.movieshow.entity.Movie;

@FeignClient(name = "movie-service")
public interface MovieClient 
{
@LoadBalanced
@GetMapping("/getmovie/movieid/{id}")
public Optional<Movie> getMovieById(@PathVariable int movieid);
}