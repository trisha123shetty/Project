package library;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import  java.util.Scanner;

import library.Book;
public class Library {

		// TODO Auto-generated method stub
		
	Book objBook = new Book();
	Scanner sc= new Scanner(System.in);
	DatabaseConnection objdb= new DatabaseConnection();


//	    ArrayList<Book> books = new ArrayList<>();
	public void addBook() {
		try {
		Connection con= DriverManager.getConnection(objdb.url, objdb.username, objdb.password);

		PreparedStatement pstmt = con.prepareStatement("call AddBook(?,?,?)");
		
		System.out.println("Enter book Name");
		String title=sc.nextLine();
		objBook.setTitle(title);
		
		System.out.println("Enter author name:");
		String author=sc.nextLine();
		objBook.setAuthor(author);
		
		System.out.println("Enter the book is available or not");
		boolean isAvailable = sc.nextBoolean();
		objBook.setIsAvailable(isAvailable);
		
		pstmt.setString(1, objBook.getTitle());
		pstmt.setString(2, objBook.getAuthor());
		pstmt.setBoolean(3, objBook.getIsAvailable());
		
		pstmt.execute();
		
		System.out.println("Book added successfully");
		System.out.println();
		
		pstmt.close();
		con.close();

	   		
		}
		
	
		
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void viewAllBook() {
		try {
		Connection con= DriverManager.getConnection(objdb.url, objdb.username, objdb.password);

		PreparedStatement pstmt = con.prepareStatement("call sp_viewBookAdmin");
		
		ResultSet res=pstmt.executeQuery();
		while(res.next()) {
			int BookId = res.getInt("bookId");
			String title = res.getString("title");
			String author= res.getString("author");
			boolean available = res.getBoolean("isAvailable");
			System.out.println("ID: " + BookId + ", Title: " + title + ", Author: " + author+"available"+ available);
			
		}
	}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	

}
