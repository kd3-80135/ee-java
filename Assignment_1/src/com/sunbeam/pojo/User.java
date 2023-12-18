package com.sunbeam.pojo;

import java.util.Date;

import com.sunbeam.util.DateUtill;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Date birth;
	private String mobileNo;

	public User() {
		this(0, "", "", "", "", null,"");
	}

	public User(int id, String firstName, String lastName, String email, String password, Date birth, String mobileNo) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.birth = birth;
		this.mobileNo = mobileNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public void setMobile(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getMobile() {
		return this.mobileNo;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password  + ", Mobile = " + mobileNo + "]";
	}
}
