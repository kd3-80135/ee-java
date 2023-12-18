package com.sunbeam.pojo;

import java.sql.Timestamp;

public class Review_Movie {
	private String review;
	private int rating;
	private Timestamp modified;
	private String title;
	
	public Review_Movie() {
		
	}

	public Review_Movie(String review, int rating, Timestamp modified, String title) {
		this.review = review;
		this.rating = rating;
		this.modified = modified;
		this.title = title;
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

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Review_Movie [review=" + review + ", rating=" + rating + ", modified=" + modified + ", title=" + title
				+ "]";
	}
	
	

}
