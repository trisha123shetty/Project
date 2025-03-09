package library;
import java.sql.*;

public class DatabaseConnection  {

	 String url= "jdbc:mysql://localhost:3306/library";
	 String username = "root";
	 String password = "root";
	public void  DatabaseConnect() throws ClassNotFoundException {
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");
//	System.out.println("connection Successfull");
	}
	catch(ClassNotFoundException e) {
		System.out.println(e.getMessage());
	}
	}

}
