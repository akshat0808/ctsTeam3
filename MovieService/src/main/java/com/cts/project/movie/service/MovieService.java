package com.cts.project.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cts.project.movie.entity.Movie;

public interface MovieService 
{
	Page<Movie> getAllMovie(Pageable pageable);
	List<Movie> getMovieById(int movieid);
	Movie createMovie(Movie movie);
	Movie updateMovie(int movieId, Movie movie);
	Optional<Movie> deleteMovie(int movieId);
}
