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
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet res=null;

//	    ArrayList<Book> books = new ArrayList<>();
	public void addBook() {
		try {
		 con= DriverManager.getConnection(objdb.url, objdb.username, objdb.password);

		 pstmt = con.prepareStatement("call AddBook(?,?,?)");
		
		System.out.print("Enter book Name");
		String title=sc.nextLine();
		objBook.setTitle(title);
		
		System.out.print("Enter author name:");
		String author=sc.nextLine();
		objBook.setAuthor(author);
		
		System.out.print("Enter the book is available or not");
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

		pstmt = con.prepareStatement("call sp_viewBookAdmin");
		
	 res=pstmt.executeQuery();
		while(res.next()) {
			int BookId = res.getInt("bookId");
			String title = res.getString("title");
			String author= res.getString("author");
			boolean available = res.getBoolean("isAvailable");
			System.out.println("ID: " + BookId + ", Title: " + title + ", Author: " + author+"available"+ available);
			System.out.println();
			
		}
	}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(res!=null || pstmt!=null|| con!=null) {
			res.close();
			pstmt.close();
			con.close();
				}
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	public void issueBook() {
		try {
		con = DriverManager.getConnection(objdb.url,objdb.username, objdb.password);
		pstmt=con.prepareStatement("call sp_issueBook(?)");
		System.out.println("Enter the bookid to be issued");
		int bookId=sc.nextInt();
		
		pstmt.setInt(1,bookId);
		
		pstmt.execute();
		System.out.println(bookId+" is issued");
		
	}catch(SQLException e) {
		System.out.println(e.getMessage());
	}
	}

}
