package com.sunbeam.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.sunbeam.pojo.Movies;
import com.sunbeam.pojo.Review_Movie;
import com.sunbeam.pojo.Reviews;
import com.sunbeam.pojo.Shares;
import com.sunbeam.pojo.User;
import com.sunbeam.util.DateUtill;

public class UserDao extends Dao{
	public UserDao () throws Exception
	{	
	}
	
	public int signUp(User u) throws Exception{
		String sql = "INSERT INTO users VALUES(?,?,?,?,?,?,default)";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, u.getFirstName());
			stmt.setString(2, u.getLastName());
			stmt.setString(3, u.getEmail());
			stmt.setString(4, u.getPassword());
			stmt.setString(5, u.getMobile());
			stmt.setDate(6, DateUtill.utilToSqlDate(u.getBirth()));
			int count = stmt.executeUpdate();
			return count;
		}
	}
	
	public User signIn (String email) throws Exception{
		String sql = "select email,password,user_id from users where email = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, email);
			try (ResultSet rs = stmt.executeQuery()){
				while (rs.next()) {
					String email1 = rs.getString("email");
					String pass = rs.getString("password");
					int user_id = rs.getInt("user_id");
					User u = new User();
					u.setEmail(email1);
					u.setPassword(pass);
					u.setId(user_id);
					
					return u;
				}
			}
		}
		return new User();
	}
	
	public int updateProfile (User u, int choice)throws Exception
	{
		switch (choice) {
			case 1:
				String sql = "update users set fname =? where email=?";
				try(PreparedStatement stmt = con.prepareStatement(sql)) 
				{
					stmt.setString(1, u.getFirstName());
					stmt.setString(2, u.getEmail());
					int count=stmt.executeUpdate();
					return count;
					
				}
			
				
			case 2:
				String sql1 = "update users set lname = ? where email=?";
				try(PreparedStatement stmt = con.prepareStatement(sql1)) 
				{
					stmt.setString(1, u.getLastName());
					stmt.setString(2, u.getEmail());
					int count=stmt.executeUpdate();
					return count;
					
				}
				
				
			case 3:
				String sql2 = "update users set password = ? where email=?";
				try(PreparedStatement stmt = con.prepareStatement(sql2)) 
				{
					stmt.setString(1, u.getPassword());
					stmt.setString(2, u.getEmail());
					int count=stmt.executeUpdate();
					return count;
					
				}
				
				
			case 4:
				String sql3 = "update users set mobile_no = ? where email=?";
				try(PreparedStatement stmt = con.prepareStatement(sql3)) 
				{
					stmt.setString(1, u.getMobile());
					stmt.setString(2, u.getEmail());
					int count=stmt.executeUpdate();
					return count;
					
				}
				
				
			case 5:
				String sql4 = "update users set birth = ? where email=?";
				try(PreparedStatement stmt = con.prepareStatement(sql4)) 
				{
					stmt.setDate(1,DateUtill.utilToSqlDate(u.getBirth()));
				
					stmt.setString(2, u.getEmail());
					int count=stmt.executeUpdate();
					return count;
					
				}
				
				

			default:
				System.out.println("Invalid Choice");
				break;
		}
		return 0;
	}
	
	
	
	public List<String> displayMovies () throws Exception
	{
		List<String> l = new ArrayList<String>();
		String sql="select title from movies";
		try(PreparedStatement stmt = con.prepareStatement(sql))
		{
			try(ResultSet rs= stmt.executeQuery())
			{
				while(rs.next())
				{
					String title= rs.getString("title");
					l.add(title);
				}
			}
		}
		return l;
	}
	
	
	
	public List<Movies> displayNonEditedMovies (User u) throws Exception{
		List<Movies> l = new ArrayList<>();
		String sql = "select m.movie_id,title from movies m left outer join reviews r on m.movie_id = r.movie_id and r.user_id =? where r.review is null";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, u.getId());
			try (ResultSet rs = stmt.executeQuery()){
				while (rs.next()) {
					Movies m = new Movies(rs.getInt("movie_id"), rs.getString("title"), null);
					l.add(m);
				}
			}
		}
		return l;
	}
	
	
	
	public List<Movies> displayEditedMovies (User u) throws Exception{
		List<Movies> l = new ArrayList<>();
		String sql = "select m.movie_id,title from movies m left outer join reviews r on m.movie_id = r.movie_id and r.user_id =? where r.review is not null";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, u.getId());
			try (ResultSet rs = stmt.executeQuery()){
				while (rs.next()) {
					Movies m = new Movies(rs.getInt("movie_id"), rs.getString("title"), null);
					l.add(m);
				}
			}
		}
		return l;
	}
	
	
	
	public int addReview (Reviews rv) throws Exception{
		int count = 0;
		String sql = "insert into reviews values(default,?,?,?,?,now())";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, rv.getMovie_id());
			stmt.setString(2, rv.getReview());
			stmt.setInt(3, rv.getRating());
			stmt.setInt(4,rv.getUser_id());
			count = stmt.executeUpdate();
		}
		
		return count;
	}
	
	
	
	public int editReview (Reviews rv, User u) throws Exception {
		int count = 0;
		String sql = "update reviews set review =?, rating =? where user_id =? and movie_id =?";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(4, rv.getMovie_id());
			stmt.setString(1, rv.getReview());
			stmt.setInt(2, rv.getRating());
			stmt.setInt(3,rv.getUser_id());
			count = stmt.executeUpdate();
		}
		return count;
	}
	
	
	public int deleteReview (int movie_id) throws Exception {
		int count = 0;
		String sql = "delete from reviews where review_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, movie_id);
			count = stmt.executeUpdate();
		}
		return count;
	}
	
	public List<Review_Movie> displayAllReviews  () throws Exception{
		List<Review_Movie> l = new ArrayList<>();
		String sql = "select title,rating,modified,review from movies m,reviews r where m.movie_id = r.movie_id";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			try (ResultSet rs = stmt.executeQuery()){
				while (rs.next()) {
					Review_Movie rm = new Review_Movie(rs.getString("review"),rs.getInt("rating"),rs.getTimestamp("modified"),rs.getString("title"));
					l.add(rm);
				}
			}
		}
		return l;
	}
	
	
	public List<Review_Movie> displayMyReviews  (User u) throws Exception{
		List<Review_Movie> l = new ArrayList<>();
		String sql = "select title,rating,modified,review from movies m,reviews r where m.movie_id = r.movie_id and r.user_id =?";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, u.getId());
			try (ResultSet rs = stmt.executeQuery()){
				while (rs.next()) {
					Review_Movie rm = new Review_Movie(rs.getString("review"),rs.getInt("rating"),rs.getTimestamp("modified"),rs.getString("title"));
					l.add(rm);
				}
			}
		}
		return l;
	}
	
	public List<Reviews> displayMyReviews2  (User u) throws Exception{
		List<Reviews> l = new ArrayList<>();
		String sql = "select review_id,rating,modified,review from movies m,reviews r where m.movie_id = r.movie_id and r.user_id =?";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, u.getId());
			try (ResultSet rs = stmt.executeQuery()){
				while (rs.next()) {
					Reviews rm = new Reviews(rs.getInt("review_id"), 0,rs.getString("review"), rs.getInt("rating"), 0,rs.getTimestamp("modified"));
					l.add(rm);
				}
			}
		}
		return l;
	}
	
	
	public List<User> displayUser (User u) throws Exception{
		List<User> l = new ArrayList<User>();
		String sql = "select user_id,fname,lname,email from users where user_id not in (?)";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, u.getId());
			try (ResultSet rs = stmt.executeQuery()){
				while (rs.next()) {
					User us = new User(rs.getInt("user_id"), rs.getString("fname"), rs.getString("lname"), rs.getString("email"), null, null, null);
					l.add(us);
				}
			}
		}
		return l;
	}
	
	public int shareReviews (Shares s) throws Exception {
		int count = 0;
		String sql = "insert into shares values (?,?)";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, s.getUser_id());
			stmt.setInt(2, s.getReview_id());
			count = stmt.executeUpdate();
		}
		return count;
	}
	
	public List<Review_Movie> displaySharedReviews (User u) throws Exception {
		List<Review_Movie> l = new ArrayList<>();
		String sql = "select title,rating,modified,review from reviews r,shares s,movies m where r.review_id = s.review_id and r.movie_id = m.movie_id and s.user_id =?";
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, u.getId());
			try (ResultSet rs = stmt.executeQuery()){
				while (rs.next()) {
					Review_Movie rm = new Review_Movie(rs.getString("review"),rs.getInt("rating"),rs.getTimestamp("modified"),rs.getString("title"));
					l.add(rm);
				}
			}
		}
		return l;
	}
}
