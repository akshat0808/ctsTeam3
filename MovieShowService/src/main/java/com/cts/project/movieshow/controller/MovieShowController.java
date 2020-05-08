package com.cts.project.movieshow.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.cts.project.movieshow.entity.Movie;
import com.cts.project.movieshow.entity.MovieShow;
import com.cts.project.movieshow.entity.Theater;
import com.cts.project.movieshow.errorHandling.CustomErrorType;
import com.cts.project.movieshow.errorHandling.ResourceNotFoundException;
import com.cts.project.movieshow.repository.MovieShowRepository;

@RestController
@RequestMapping("/movieshow")
public class MovieShowController 
{
	  @Autowired
	  private MovieShowRepository movieShowRepository;
	 
	  @Autowired
	  private WebClient.Builder webClientBuilder;	 
	  
	  private final Logger logger = LoggerFactory.getLogger(this.getClass());

	  @RequestMapping(value = "/", method = RequestMethod.GET)
	  public ResponseEntity<List<MovieShow>> listAllMovieShow() 
	  {
	        List<MovieShow> movieshow = movieShowRepository.findAll();
	        
	        if (movieshow.isEmpty()) 
	        {
	            return new ResponseEntity<List<MovieShow>>(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<MovieShow>>(movieshow, HttpStatus.OK);
	    }
      
      
       @SuppressWarnings({ "unchecked", "rawtypes" })
	   @RequestMapping(value = "/", method = RequestMethod.POST)
       public ResponseEntity<?> createMovieShow(@RequestBody MovieShow movieShow, UriComponentsBuilder ucBuilder) 
       {
              logger.info("Creating new Movie Show : {}", movieShow);
              
              Movie movie = webClientBuilder.build() // API call for movie service
                      .get()
                      .uri("http://movie-service/movie/" + movieShow.getMovieId())
                      .retrieve().bodyToMono(Movie.class)
                      .block();
              
    	      Theater theater = webClientBuilder.build() // API call for theater service
                           .get()
                           .uri("http://theater-service/theater/" + movieShow.getTheaterId())
                           .retrieve().bodyToMono(Theater.class)
                           .block();
   
	          if (movie == null) 
	          {
	              logger.error("Unable to create Movie Show : {} as given movie does not exist", movieShow);
	              return new ResponseEntity(new CustomErrorType("Unable to create Movie Show with movie Id " + 
	                      movieShow.getMovieId() + ", as the movie does not exist."),HttpStatus.CONFLICT);
	          }
	          
	          if (theater == null) 
	          {
	              logger.error("Unable to create Movie Show : {} as given theater does not exist", movieShow);
	              return new ResponseEntity(new CustomErrorType("Unable to create Movie Show with theater Id " + 
	                      movieShow.getTheaterId() + ", as the theater does not exist."),HttpStatus.CONFLICT);
	          }
	          
	          
	          movieShowRepository.save(movieShow);
	   
	          HttpHeaders headers = new HttpHeaders();
	          headers.setLocation(ucBuilder.path("/movieshow/{movieshowId}").buildAndExpand(movieShow.getId()).toUri());
	          return new ResponseEntity<String>(headers, HttpStatus.CREATED);
       }
   

	   @RequestMapping(value = "/{movieshowId}", method = RequestMethod.PUT)
       public MovieShow updateMovieShow(@PathVariable("movieshowId") Integer movieshowId, @RequestBody MovieShow movieShowRequest) 
       {
		   logger.info("Updating Movie Show with id {}", movieshowId);
			 
    	   return movieShowRepository.findById(movieshowId).map(movieShow -> {
               movieShow.setsTime(movieShowRequest.getsTime());
               return movieShowRepository.save(movieShow);
           }).orElseThrow(() -> new ResourceNotFoundException("Movie Show Id " + movieshowId + " not found"));
   
       }


        @RequestMapping(value = "/{movieshowId}", method = RequestMethod.DELETE)
	    public ResponseEntity<?> deleteMovieShow(@PathVariable (value="movieshowId") Integer movieShowId) 
        {
        	 logger.info("Deleting Movie Showswith id {}", movieShowId);
        	 
	         return movieShowRepository.findById(movieShowId).map(movieShow -> {
	         	movieShowRepository.delete(movieShow);
	             return ResponseEntity.ok().build();
	         }).orElseThrow(() -> new ResourceNotFoundException("Movie Show Id : " + movieShowId + " not found"));
	     }
        
	      
	     @RequestMapping(value = "/", method = RequestMethod.DELETE)
	     public ResponseEntity<MovieShow> deleteAllMovieShow() 
	     {
	          logger.info("Deleting All Movie Shows...");
	   
	          movieShowRepository.deleteAll();
	          return new ResponseEntity<MovieShow>(HttpStatus.NO_CONTENT);
	     }

}
