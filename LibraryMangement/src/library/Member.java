package library;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import library.DatabaseConnection;

public class Member {
    private String name;
    private String contact;
    private String Password;
    boolean isLogin= false;

    // Setters and Getters
    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public void setPassword(String Password) {
    	this.Password= Password;
    }
    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
    public String getPassword() {
    	return Password;
    }

    public void InsertMember() {
        Scanner sc = new Scanner(System.in);
        DatabaseConnection objdb = new DatabaseConnection();

        try {
            Connection con = DriverManager.getConnection(objdb.url, objdb.username, objdb.password);

            // Use CALL to invoke the stored procedure
            PreparedStatement pSmt = con.prepareStatement("CALL sp_insertMember(?, ?)");

            // Get user input for name and contact
            System.out.print("Name: ");
            name = sc.nextLine();
            System.out.print("Contact: ");
            contact = sc.nextLine();

            pSmt.setString(1, name);  
            pSmt.setString(2, contact);

          
            int rowsAffected = pSmt.executeUpdate();
            isLogin=true;
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
