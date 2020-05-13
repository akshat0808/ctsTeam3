package com.cts.project.movieshow.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.project.movieshow.entity.MovieShow;
import com.cts.project.movieshow.repository.MovieShowRepository;


@SpringBootTest
@Runwith(SpringRunner.class)
class MovieShowControllerTest {
	
	 @MockBean
	 @Autowired
	  private MovieShowRepository movieShowRepository;

	@Test
	public void testListAllMovieShow() {
		when(movieShowRepository.findAll())
		.thenReturn(Stream.of(new MovieShow("11:30",123,111)
				, new MovieShow("01:00",124,222))
		.collect(Collectors.toList()));
		assertEquals(2, movieShowRepository.findAll().size());
	}

	@Test
	public void testCreateMovieShow() {
		MovieShow movieShow= new MovieShow("08:30",121,555);
		when(movieShowRepository.save(movieShow)).thenReturn(movieShow);
		assertEquals(movieShow, movieShowRepository.save(movieShow));
	}

	@Test
	public void testDeleteMovieShow() {
		MovieShow movieShow= new MovieShow("08:30",121,555);
		movieShowRepository.delete(movieShow);
		verify(movieShowRepository, times(1)).delete(movieShow);
	}

	@Test
	public void testDeleteAllMovieShow() {
		movieShowRepository.deleteAll();;
		verify(movieShowRepository, times(1)).deleteAll();
	}

}
