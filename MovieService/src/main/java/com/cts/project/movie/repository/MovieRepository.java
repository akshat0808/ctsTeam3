package com.cts.project.movie.repository;

import java.util.List; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.cts.project.movie.entity.Movie;

@RepositoryRestResource
public interface MovieRepository extends JpaRepository<Movie, Integer> 
{
	List<Movie> getMovieById(int movieid);

}
