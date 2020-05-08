package com.cts.project.theater.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.cts.project.theater.entity.Theater;
import com.cts.project.theater.errorHandling.CustomErrorType;
import com.cts.project.theater.errorHandling.ResourceNotFoundException;
import com.cts.project.theater.repository.TheaterRepository;

@RestController
@RequestMapping("/theater")
public class TheaterController 
{
		@Autowired
	    private TheaterRepository theaterRepository;
	
		private final Logger logger = LoggerFactory.getLogger(this.getClass());
		
		 @RequestMapping(value = "/", method = RequestMethod.GET)
		 public ResponseEntity<List<Theater>> listAllTheater() 
		 {
		        List<Theater> theater = theaterRepository.findAll();
		        
		        if (theater.isEmpty()) {
		            return new ResponseEntity<List<Theater>>(HttpStatus.NO_CONTENT);
		            // You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<List<Theater>>(theater, HttpStatus.OK);
		  }
		 
		 
		 @RequestMapping(value = "/{theaterId}", method = RequestMethod.GET)
		 public ResponseEntity<?> getTheater(@PathVariable("theaterId") Integer theaterId) 
	     {
			 logger.info("Fetching Theater with id {}", theaterId);
			 Theater theater = theaterRepository.findById(theaterId).orElse(null);;
			 
			 if (theater == null) 
			 {
				logger.error("Theater with id {} not found.", theaterId);
				return new ResponseEntity<Theater>(theater, HttpStatus.NOT_FOUND);
			 }
			
			 return new ResponseEntity<Theater>(theater, HttpStatus.OK);
		 }
	   
	     @SuppressWarnings({ "unchecked", "rawtypes" })
		 @RequestMapping(value = "/", method = RequestMethod.POST)
	    public ResponseEntity<?> createTheater(@Valid @RequestBody Theater theater, UriComponentsBuilder ucBuilder) throws IOException 
	    {
	   	   logger.info("Creating new Theater : {}", theater);
	   	 
	   	   if (theaterRepository.existsTheaterBytCode(theater.gettCode() )) 
	   	   {
	            logger.error("Unable to create Thater with name {} already code", theater.gettCode());
	            return new ResponseEntity(new CustomErrorType("Unable to create Theater with code " +  theater.gettCode() + " already exist."),HttpStatus.CONFLICT);
	       }
	        
	   	   theaterRepository.save(theater);
	       
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/theater/{theaterId}").buildAndExpand(theater.getId()).toUri());
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	    }
	
	    @RequestMapping(value = "/{theaterId}", method = RequestMethod.PUT)
	    public Theater updateTheater(@PathVariable Integer theaterId, @Valid @RequestBody Theater theaterRequest) 
	    {
	       return theaterRepository.findById(theaterId).map(theater -> {
	    	   theater.settName(theaterRequest.gettName());
	           return theaterRepository.save(theater);
	       }).orElseThrow(() -> new ResourceNotFoundException(" Theater Id: " + theaterId + " not found"));
	    }
	
	
	    @RequestMapping(value = "/{theaterId}", method = RequestMethod.DELETE)
		 public ResponseEntity<?> deleteTheater(@PathVariable (value="theaterId") Integer theaterId) 
	    {
	    	 logger.info("Deleting Theater with id {}", theaterId);
	    	 
		         return theaterRepository.findById(theaterId).map(theater -> {
		        	 theaterRepository.delete(theater);
		             return ResponseEntity.ok().build();
		         }).orElseThrow(() -> new ResourceNotFoundException("Theater Id : " + theaterId + " not found"));
		  }
	
	     
	     @RequestMapping(value = "/", method = RequestMethod.DELETE)
	     public ResponseEntity<Theater> deleteAllTheater() 
	     {
	         logger.info("Deleting All Theaters...");
	  
	         theaterRepository.deleteAll();
	         return new ResponseEntity<Theater>(HttpStatus.NO_CONTENT);
	     }
}
