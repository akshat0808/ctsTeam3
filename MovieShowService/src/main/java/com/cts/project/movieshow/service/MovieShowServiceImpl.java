package com.cts.project.movieshow.service;

import java.util.List;
import java.util.Optional;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cts.project.movieshow.entity.MovieShow;
import com.cts.project.movieshow.repository.MovieShowRepository;

public class MovieShowServiceImpl implements MovieShowService
{
	@Autowired
	private MovieShowRepository movieShowRepository;
	
//	@Autowired
//	private MovieClient movieClient;
//	
//	@Autowired
//	private TheaterClient theaterClient;
//	
	//private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public Page<MovieShow> getAllMovieShow(Pageable pageable) {
		// TODO Auto-generated method stub
		return movieShowRepository.findAll(pageable);
	}

	@Override
	public List<MovieShow> getMovieShowById(int movieShowid) {
		// TODO Auto-generated method stub
		return movieShowRepository.getMovieShowById(movieShowid);
	}

	@Override
	public MovieShow createMovieShow(MovieShow movieShow) {
//		System.out.println(movieShow.getId());
//		Optional<Movie> m = movieClient.getMovieById(movieShow.getMovieId());
//		Optional<Theater> t = theaterClient.getTheaterById(movieShow.getThreaterId());
//		
////		log.debug();
//		Theater theater = t.get();
//		Movie movie = m.get();
//		MovieShow rental = new MovieShow();
//		
//		rental.setId(movieShow.getId());
//		rental.setsTime(movieShow.getsTime());
//		rental.setMovieId(movieShow.getMovieId());
//		rental.setThreaterId(movieShow.getThreaterId());
//		
//		rental.setMovieId(movie.getId());
//		rental.setThreaterId(theater.getId());
//		
		return movieShowRepository.save(movieShow);

	}

	@Override
	public MovieShow updateMovieShow(int movieShowId, MovieShow movieShow) {
		// TODO Auto-generated method stub
		return movieShowRepository.save(movieShow);
	}
	
	@Override
	public Optional<MovieShow> deleteMovieShow(int movieShowId) {
		// TODO Auto-generated method stub
		return movieShowRepository.findById(movieShowId);
	}

	

}
