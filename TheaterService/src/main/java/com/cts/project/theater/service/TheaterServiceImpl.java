package com.cts.project.theater.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cts.project.theater.entity.Theater;
import com.cts.project.theater.repository.TheaterRepository;

public class TheaterServiceImpl implements TheaterService 
{
	@Autowired
	 TheaterRepository theaterRepository;
	
	@Override
	public Page<Theater> getAllTheater(Pageable pageable) {
		// TODO Auto-generated method stub
		return theaterRepository.findAll(pageable);
	}

	@Override
	public List<Theater> getTheaterById(int theaterid) {
		// TODO Auto-generated method stub
		return theaterRepository.getTheaterById(theaterid);
	}

	@Override
	public Theater createTheater(Theater theater) {
		// TODO Auto-generated method stub
		return theaterRepository.save(theater);
	}

	@Override
	public Theater updateTheater(int theaterId, Theater theater) {
		// TODO Auto-generated method stub
		return theaterRepository.save(theater);
	}

	@Override
	public Optional<Theater> deleteTheater(int theaterId) {
		// TODO Auto-generated method stub
		return theaterRepository.findById(theaterId);
	}

}
