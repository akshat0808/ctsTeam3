package com.cts.project.movieshow.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.project.movieshow.entity.MovieShow;

@Repository
public interface MovieShowRepository extends JpaRepository<MovieShow, Integer> 
{
	List<MovieShow> getMovieShowById(int movieShowId);

}
