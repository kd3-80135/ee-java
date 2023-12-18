package com.sunbeam.pojo;

import java.sql.Date;

public class Movies {
	private int movieId;
	private String title;
	private Date release;
	
	public Movies() {
		this.movieId = 0;
		this.title = "";
		this.release = null;
	}

	public Movies(int movieId, String title, Date release) {
		this.movieId = movieId;
		this.title = title;
		this.release = release;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	@Override
	public String toString() {
		return "Movies [movieId=" + movieId + ", title=" + title + ", release=" + release + "]";
	}


}
