package com.cts.project.movie.controller;

import com.cts.project.movie.entity.Movie;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class EmptyJsonResponse extends Movie {

	public EmptyJsonResponse(int i, String string) {
		super(i, string);
		// TODO Auto-generated constructor stub
	}

}