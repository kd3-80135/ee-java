package com.sunbeam.tester;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.sunbeam.dao.UserDao;
import com.sunbeam.pojo.Movies;
import com.sunbeam.pojo.Review_Movie;
import com.sunbeam.pojo.Reviews;
import com.sunbeam.pojo.Shares;
import com.sunbeam.pojo.User;
import com.sunbeam.util.DateUtill;

public class Tester {
	
	public static int menu1 (Scanner sc) {
		int choice;
		
		System.out.println("*************************************************************");
		System.out.println("0. To exit.");
		System.out.println("1. Sign Up.");
		System.out.println("2. Sign In.");
		System.out.println("*************************************************************");
		System.out.println("Enter your choice : ");
		choice = sc.nextInt();
		
		return choice;
	}
	
	public static int menu2 (Scanner sc) {
		int choice;
		
		System.out.println("*************************************************************");
		System.out.println("0. To exit.");
		System.out.println("1. Edit Profile.");
		System.out.println("2. Display all movies.");
		System.out.println("3. Create a review.");
		System.out.println("4. Edit review.");
		System.out.println("5. Delete review.");
		System.out.println("6. Display all reviews.");
		System.out.println("7. Display my reviws.");
		System.out.println("8. Share reviews.");
		System.out.println("9. Display reviews shared with me.");
		System.out.println("*************************************************************");
		System.out.println("Enter your choice : ");
		choice = sc.nextInt();
		
		return choice;
	}
	
	public static int EditProfileMenu(Scanner sc) {
		int choice;
		
		System.out.println("*************************************************************");
		System.out.println("0. To exit.");
		System.out.println("1. Edit First Name.");
		System.out.println("2. Edit Last Name.");
		System.out.println("3. Edit password.");
		System.out.println("4. Edit Mobile Number.");
		System.out.println("5. Edit Birth Date.");
		System.out.println("*************************************************************");
		System.out.println("Enter your choice : ");
		choice = sc.nextInt();
		
		return choice;
	}
	
	
	
	public static void EditProfile (User u,Scanner sc) throws Exception {
		int choice;
		while((choice=EditProfileMenu(sc))!=0)
		{ 	
			switch (choice) {
			case 1:
				try(UserDao ud= new UserDao()){
					System.out.println("Enter the new First Name");
					String fName= sc.next();
					u.setFirstName(fName);
					int count = ud.updateProfile(u, 1);
					if(count==1)
					{
						System.out.println("First Name Changed");
					}
					
				}
				break;
				
			case 2:try(UserDao ud= new UserDao()){
						System.out.println("Enter the new Last Name");
						String lName= sc.next();
						u.setLastName(lName);;
						int count = ud.updateProfile(u, 2);
						if(count==1)
						{
							System.out.println("Last Name Changed");
						}
					}
					break;
			
			case 3:
				try(UserDao ud= new UserDao()){
					System.out.println("Enter the new password");
					String email= sc.next();
					u.setPassword(email);
					int count = ud.updateProfile(u, 3);
					if(count==1)
					{
						System.out.println("Password Changed");
					}
				}
				break;
				
			case 4:
				try(UserDao ud= new UserDao()){
					System.out.println("Enter the new Mobile Number");
					String mobile_no= sc.next();
					u.setMobile(mobile_no);
					int count = ud.updateProfile(u, 4);
					if(count==1)
					{
						System.out.println("Mobile Number Changed");
					}
				}
				break;
				
			case 5:
				try(UserDao ud= new UserDao()){
					System.out.println("Enter the new Birth Date");
					String BirthDate= sc.next();
					u.setBirth(DateUtill.parse(BirthDate));
					int count = ud.updateProfile(u, 5);
					if(count==1)
					{
						System.out.println("Birth Date Changed");
					}
				}
				break;
			
			default:
				System.out.println("Invalid Choice");
				break;
			}
		}
	}
	
	

	public static void main(String[] args) {
		int choice;
		Scanner sc = new Scanner(System.in);
		User u = new User();
		
		while ((choice = menu1(sc)) != 0) {
			switch (choice) {
				case 1: try (UserDao ud = new UserDao()){
							System.out.println("Enter your details to sign up : ");
							System.out.print("Enter your First Name : ");
							sc.nextLine();
							String f_name = sc.nextLine();
							System.out.print("Enter your Last Name : ");
							String l_name = sc.nextLine();
							System.out.print("Enter your email : ");
							String email = sc.nextLine();
							System.out.print("Enter your password : ");
							String password = sc.nextLine();
							System.out.print("Enter your mobile number : ");
							String mobile = sc.nextLine();
							System.out.print("Enter birth date : ");
							String birth = sc.next();
							u.setBirth(DateUtill.parse(birth));
							u.setEmail(email);
							u.setFirstName(f_name);
							u.setLastName(l_name);
							u.setMobile(mobile);
							u.setPassword(password);
							//User u = new User(0, f_name, l_name, email, password, DateUtill.parse(birth), mobile);
							
							int count = ud.signUp(u);
							if (count == 1) {
								System.out.println("Sign Up succesfull.");
							}
							else {
								System.out.println("Sign Up failed.");
							}
						}
						catch (Exception e) {
							e.printStackTrace();
						}
						break;
						
				case 2: try (UserDao ud = new UserDao()){
							System.out.print("Enter your email : ");
							String email = sc.next();
							System.out.print("Enter your password : ");
							String password = sc.next();
							u = ud.signIn(email);
							if (u.getId() ==  0) {
								System.err.println("Username does not exit.");
							}
							else if (u.getPassword().equals(password)) {
								System.out.println("Login Successful");
								int choice2;
								List<String> l = new ArrayList<String>();
								while ((choice2 = menu2(sc)) != 0) {
									switch (choice2) {
										case 1: EditProfile(u,sc);
												break;
												
										case 2:
												l = ud.displayMovies();
												for (String string : l) {
													System.out.println(string);
												}
												break;
											
										case 3: List<Movies> list = new ArrayList<>();
												list = ud.displayNonEditedMovies(u);
												for (Movies m : list) {
													System.out.println(m.getMovieId() + "  " + m.getTitle() );
												}
												System.out.print("Enter the movie number to add a review : ");
												int movie = sc.nextInt();
												String rev = "";
												System.out.println("Enter your review ");
												sc.nextLine();
												rev = sc.nextLine();
												while (rev.length() < 15) {
													System.out.println("Enter your review: ");
													rev = sc.nextLine();
													if (rev.length()<15) {
														System.out.println("Review too short (min length 15).");
													}
												}
												int rating;
												do {
													System.out.print("Enter the rating for the movie :");
													rating = sc.nextInt();
												}
												while (rating < 0 || rating >10);
												
												Reviews rv = new Reviews(0, movie, rev, rating, u.getId(),null);
												
												int count = ud.addReview(rv);
												if (count == 1) {
													System.out.println("Review added.");
												}
												break;
											
										case 4: List<Movies> l2 = new ArrayList<Movies>();
												l2 = ud.displayEditedMovies(u);
												for (Movies m : l2) {
													System.out.println(m.getMovieId() + "  " + m.getTitle() );
												}
												System.out.print("Enter the movie number to add a review : ");
												int movie2 = sc.nextInt();
												System.out.println("Enter your review ");
												sc.nextLine();
												rev = sc.nextLine();
												while (rev.length() < 15) {
													System.out.println("Enter your review: ");
													rev = sc.nextLine();
													if (rev.length()<15) {
														System.out.println("Review too short (min length 15).");
													}
												}
												int rating2;
												do {
													System.out.print("Enter the rating for the movie :");
													rating2 = sc.nextInt();
												}
												while (rating2 < 0 || rating2 >10);
												
												Reviews rv2 = new Reviews(0, movie2, rev, rating2, u.getId(),null);
												
												int count2 = ud.editReview(rv2, u);
												if (count2 == 1) {
													System.out.println("Review edited.");
												}
												break;
											
										case 5: List<Reviews> l3 = new ArrayList<>();
												l3 = ud.displayMyReviews2(u);
												for (Reviews m : l3) {
													System.out.println(m.getId() + "  "+m.getRating()+ "  "+m.getModified() +"  "+m.getReview() );
												}
												System.out.print("Enter the movie number to delete the review : ");
												int movie3 = sc.nextInt();
												int count3 = ud.deleteReview(movie3);
												if (count3 == 1) {
													System.out.println("Review deleted.");
												}
												break;
											
										case 6: List<Review_Movie> l4 = new ArrayList<>();
												
												l4 = ud.displayAllReviews();
												for (Review_Movie rm : l4) {
													System.out.println(rm.getTitle() + "  "+rm.getRating()+ "  "+rm.getModified() +"  "+rm.getReview() );
												}
												break;
											
										case 7: List<Review_Movie> l6 = new ArrayList<>();
										
												l6 = ud.displayMyReviews (u);
												for (Review_Movie rv3 : l6) {
													System.out.println(rv3.getTitle() + "  "+rv3.getRating()+ "  "+rv3.getModified() +"  "+rv3.getReview() );
												}
												break;
											
										case 8: List<User> lu = new ArrayList<User>();
												List<Reviews> lr = new ArrayList<Reviews>();
												lr = ud.displayMyReviews2(u);
												lu = ud.displayUser(u);
												for (Reviews r : lr) {
													System.out.println(r);
												}
												System.out.println("Enter review id to be shared : ");
												int review = sc.nextInt();
												for (User user : lu) {
													System.out.println(user);
												}
												int i = 0;
												List<Integer> arr = new ArrayList<Integer>();
												System.out.println("Enter the users you want to share the review with : ");
												while (true) {
													int a = sc.nextInt();
													if (a == 0) {
														break;
													}
													arr.add(a);
												}
												Shares s = new Shares();
												for (Integer in :arr ) {
													s.setReview_id(review);
													s.setUser_id(in);
													int counter = ud.shareReviews(s);
													if (counter == 1) {
														System.out.println("Review shared with user " + in);
													}
												}
												break;
											
										case 9:	System.out.println("The reviews shared with you are :");
												List<Review_Movie> lm = new ArrayList<Review_Movie>();
												lm = ud.displaySharedReviews(u);
												for (Review_Movie rm : lm)
												System.out.println(rm);
												break;
									}
								}				
							}
						}
						catch (Exception e) {
							e.printStackTrace();
						}
						break;	
				}
			}
		}
	}
