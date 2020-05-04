package com.cts.project.movieshow.service;

import java.util.List; 
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cts.project.movieshow.entity.MovieShow;

public interface MovieShowService 
{
	Page<MovieShow> getAllMovieShow(Pageable pageable);
	List<MovieShow> getMovieShowById(int movieShowid);
	MovieShow createMovieShow(MovieShow movieShow);
	MovieShow updateMovieShow(int movieShowId, MovieShow movieShow);
	Optional<MovieShow> deleteMovieShow(int movieShowId);
}
