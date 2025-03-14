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
		}finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
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
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
public void issueBook() {
    try {
        con = DriverManager.getConnection(objdb.url, objdb.username, objdb.password);
        pstmt = con.prepareStatement("call sp_issueBook(?)");
        System.out.println("Enter the bookId to be issued:");
        int bookId = sc.nextInt();
        
        pstmt.setInt(1, bookId);
        
        pstmt.execute();
        System.out.println(bookId + " is issued");
        
    } catch(SQLException e) {
        System.out.println(e.getMessage());
    }finally {
        try {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

public void deleteBook() {
	 try {
	        con = DriverManager.getConnection(objdb.url, objdb.username, objdb.password);
	        pstmt = con.prepareStatement("call sp_deleteBook(?)");
	        System.out.println("Enter the bookId to be deleted:");
	        int bookId = sc.nextInt();
	        
	        pstmt.setInt(1, bookId);
	        
	        pstmt.execute();
	        System.out.println(bookId + " is deleted");
	        
	    } catch(SQLException e) {
	        System.out.println(e.getMessage());
	    }finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
}

public void SearchBook() {
    try {
        con = DriverManager.getConnection(objdb.url, objdb.username, objdb.password);
        
        // Prepare stored procedure call
        pstmt = con.prepareStatement("{CALL sp_searchBookByName(?)}");
        System.out.print("please enter a book name you want to search?");
        String bookName=sc.nextLine();
        pstmt.setString(1, bookName);  // Set book name parameter
        
        res = pstmt.executeQuery();
        
        boolean found = false;
        while (res.next()) {
            found = true;
            int bookId = res.getInt("bookId");
            String title = res.getString("title");
            String author = res.getString("author");
            boolean available = res.getBoolean("isAvailable");

            System.out.println("ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Available: " + available);
            System.out.println();
        }
        
        if (!found) {
            System.out.println("No books found with the name: " + bookName);
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    } finally {
        try {
            if (res != null) res.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}


public void UserViewBook() {
    try {
        con = DriverManager.getConnection(objdb.url, objdb.username, objdb.password);
        
        // Use CALL instead of EXEC
        pstmt = con.prepareStatement("{CALL sp_viewUserBook()}");
        
        res = pstmt.executeQuery();
        
        while (res.next()) {
            int bookId = res.getInt("bookId");
            String title = res.getString("title");
            String author = res.getString("author");
            boolean available = res.getBoolean("isAvailable");
            
            System.out.println("ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Available: " + available);
            System.out.println();
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } finally {
        try {
            if (res != null) res.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

public void returnBook() {
    try {
        con = DriverManager.getConnection(objdb.url, objdb.username, objdb.password);
        
        // Call the stored procedure
        pstmt = con.prepareStatement("{CALL sp_returnBook(?)}");
        System.out.print("Please enter book name to be returned:");
        String title = sc.nextLine();
        pstmt.setString(1, title);
        
        int rowsAffected = pstmt.executeUpdate(); // Use executeUpdate() instead of executeQuery()
        
        if (rowsAffected > 0) {
            System.out.println(title + " returned to the book store");
        } else {
            System.out.println("Book not found or already available.");
        }
        
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}



}
