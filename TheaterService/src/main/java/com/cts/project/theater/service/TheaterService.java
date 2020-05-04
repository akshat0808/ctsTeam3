package com.cts.project.theater.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cts.project.theater.entity.Theater;

public interface TheaterService 
{
	Page<Theater> getAllTheater(Pageable pageable);
	List<Theater> getTheaterById(int theaterid);
	Theater createTheater(Theater theater);
	Theater updateTheater(int theaterId, Theater theater);
	Optional<Theater> deleteTheater(int theaterId);
}
