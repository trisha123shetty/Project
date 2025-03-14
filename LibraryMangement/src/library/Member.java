package library;
import java.sql.Connection;
import java.sql.ResultSet;
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
    DatabaseConnection objdb = new DatabaseConnection();

    
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
    public boolean isAdmin(String username, String password) {
        boolean isAdmin = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;

        try {
             con = DriverManager.getConnection(objdb.url, objdb.username, objdb.password);
            pstmt = con.prepareStatement("SELECT * FROM Admin WHERE adminName = ? AND password = ?");
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            res = pstmt.executeQuery();
            if (res.next()) {
                isAdmin = true;
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
        return isAdmin;
    }

    public void InsertMember() {
        Scanner sc = new Scanner(System.in);

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
