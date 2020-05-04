package com.cts.project.movieshow.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rental")

public class MovieShow 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Column(name = "stime")
	private Time sTime;
	@NotNull
	@Column(name = "movieid")
	private int movieId;
	@NotNull
	@Column(name = "theaterid")
	private int threaterId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Time getsTime() {
		return sTime;
	}
	public void setsTime(Time sTime) {
		this.sTime = sTime;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getThreaterId() {
		return threaterId;
	}
	public void setThreaterId(int threaterId) {
		this.threaterId = threaterId;
	}
	
}		

