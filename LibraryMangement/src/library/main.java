package library;
import java.util.Scanner;
import library.Member;
import library.DatabaseConnection;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String password;
		
		Scanner sc = new Scanner(System.in);
		try {
		DatabaseConnection objDatabase = new DatabaseConnection();
		objDatabase.DatabaseConnect();
		}catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		System.out.println("Welcome to our library");
		System.out.println("Please provide ur Login details");

		Member objMember = new Member();
		objMember.InsertMember();
		
		System.out.println("Enter Password");
        password=sc.next();
		
		
		
		String Membername = objMember.getName();
		String adminPassword = objMember.getPassword();
		
		if(Membername.equals("Trisha") && password.equals("9513") ) {
			
			System.out.println("Details will be displayed");
		}else {
			System.out.println("not displayed");
		}
		
		

	}

}
