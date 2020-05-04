package com.cts.project.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cts.project.movie.entity.Movie;
import com.cts.project.movie.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService
{
	@Autowired
	 MovieRepository movieRepository;

	@Override
	public Page<Movie> getAllMovie(Pageable pageable) {
		return movieRepository.findAll(pageable);
	}

	@Override
	public List<Movie> getMovieById(int movieid) {
		// TODO Auto-generated method stub
		return movieRepository.getMovieById(movieid);
	}

	@Override
	public Movie createMovie(Movie movie) {
		// TODO Auto-generated method stub
		return movieRepository.save(movie);
	}

	@Override
	public Movie updateMovie(int movieId, Movie movie) {
		// TODO Auto-generated method stub
		return movieRepository.save(movie);
	}

	@Override
	public Optional<Movie> deleteMovie(int movieId) {
		// TODO Auto-generated method stub
		return movieRepository.findById(movieId);
	}

}
