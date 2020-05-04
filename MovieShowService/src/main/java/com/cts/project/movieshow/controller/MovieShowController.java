package com.cts.project.movieshow.controller;

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

import com.cts.project.movieshow.entity.MovieShow;
import com.cts.project.movieshow.exceptions.ResourceNotFoundException;
import com.cts.project.movieshow.repository.MovieShowRepository;


public class MovieShowController 
{
	 @Autowired
	 private MovieShowRepository movieShowRepository;
	 
//	 @Autowired
//	 private WebClient.Builder webClientBuilder;	
//	 
//    // Api call
//     Movie movie = webClientBuilder.build()
//             .get()
//             .uri("http://localhost:8000/movieid")
//             .retrieve().bodyToMono(Movie.class)
//             .block();
     
     @GetMapping("/movieshow") 
     public Page<MovieShow> getAllMovieShow(Pageable pageable) throws IOException {
         return movieShowRepository.findAll(pageable);
     }

     @GetMapping("/movieshow/{movieshowid}")
 	 public List<MovieShow> getMovieShow(@PathVariable int movieShowId) throws IOException {	 
 		 return movieShowRepository.getMovieShowById(movieShowId); 
     }
     
     @PostMapping("/movieshow")
     public MovieShow createMovieShow(@Valid @RequestBody MovieShow movieShow) throws IOException {
         return movieShowRepository.save(movieShow);
     }

     @PutMapping("/movieshow/{movieshowId}")
     public MovieShow updateMovieShow(@PathVariable Integer movieShowId, @Valid @RequestBody MovieShow movieShowRequest) {
         return movieShowRepository.findById(movieShowId).map(movieShow -> {
             movieShow.setsTime(movieShowRequest.getsTime());
             return movieShowRepository.save(movieShow);
         }).orElseThrow(() -> new ResourceNotFoundException("Movie Show Id : " + movieShowId + " not found"));
     }


     @DeleteMapping("/movieshow/{movieshowid}")
     public ResponseEntity<?> deleteMovieShow(@PathVariable Integer movieShowId) {
         return movieShowRepository.findById(movieShowId).map(movieShow -> {
         	movieShowRepository.delete(movieShow);
             return ResponseEntity.ok().build();
         }).orElseThrow(() -> new ResourceNotFoundException("Movie Show Id : " + movieShowId + " not found"));
     }

}
