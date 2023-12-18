package com.sunbeam.pojo;

public class Shares {
	private int user_id;
	private int review_id;
	
	public Shares() {
		// TODO Auto-generated constructor stub
	}

	public Shares(int user_id, int review_id) {
		this.user_id = user_id;
		this.review_id = review_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	@Override
	public String toString() {
		return "Shares [user_id=" + user_id + ", review_id=" + review_id + "]";
	}
}
