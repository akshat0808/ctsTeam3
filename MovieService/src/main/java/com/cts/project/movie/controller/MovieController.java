package com.cts.project.movie.controller;

import java.io.IOException; 
import java.util.List;

import javax.validation.Valid; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.project.movie.entity.Movie;
import com.cts.project.movie.exceptions.ResourceNotFoundException;
import com.cts.project.movie.repository.MovieRepository;

@RestController
public class MovieController 
{
	@Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movie") 
    public Page<Movie> getAllMovie(Pageable pageable) throws IOException {
        return movieRepository.findAll(pageable);
    }

    @GetMapping("/movie/{movieid}")
	 public List<Movie> getMovie(@PathVariable int movieid) throws IOException {	 
		 return movieRepository.getMovieById(movieid); 
    }
    
    @PostMapping("/movie")
    public Movie createMovie(@Valid @RequestBody Movie movie) throws IOException {
        return movieRepository.save(movie);
    }

    @PutMapping("/movie/{movieId}")
    public Movie updateMovie(@PathVariable Integer movieId, @Valid @RequestBody Movie movieRequest) {
        return movieRepository.findById(movieId).map(movie -> {
            movie.setmName(movieRequest.getmName());
            return movieRepository.save(movie);
        }).orElseThrow(() -> new ResourceNotFoundException("MovieId " + movieId + " not found"));
    }


    @DeleteMapping("/movie/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer movieId) {
        return movieRepository.findById(movieId).map(movie -> {
        	movieRepository.delete(movie);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("MovieId " + movieId + " not found"));
    }


}
