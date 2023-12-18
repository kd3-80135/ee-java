package com.sunbeam.pojo;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reviews {
	private int id;
	private int movie_id;
	private String review;
	private int rating;
	private int user_id;
	private java.sql.Timestamp modified;
	
	public Reviews(int id, int movie_id, String review, int rating, int user_id, java.sql.Timestamp modified) {
		this.id = id;
		this.movie_id = movie_id;
		this.review = review;
		this.rating = rating;
		this.user_id = user_id;
		this.modified = modified;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public java.sql.Timestamp getModified() {
		return modified;
	}
	public void setModified(java.sql.Timestamp modified) {
		this.modified = modified;
	}
	@Override
	public String toString() {
		return "Reviews [id=" + id + ", movie_id=" + movie_id + ", review=" + review + ", rating=" + rating
				+ ", user_id=" + user_id + ", modified=" + modified + "]";
	}
	
	
	
	
}
