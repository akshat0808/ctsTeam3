package com.cts.project.theater.repository;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.cts.project.theater.entity.Theater;

@RepositoryRestResource
public interface TheaterRepository extends JpaRepository<Theater, Integer>
{

	List<Theater> getTheaterById(int theaterid);


}
