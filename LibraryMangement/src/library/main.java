package library;
import java.lang.classfile.instruction.SwitchCase;
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
		
		Library objLib = new Library();
		
		System.out.print("Enter Password");
        password=sc.next();
		
		String Membername = objMember.getName();
		String adminPassword = objMember.getPassword();
		if(objMember.isLogin) {
			boolean exit=false;
			while(!exit) {
		if(Membername.equals("Trisha") && password.equals("9513") ) {
//			int choice=sc.nextInt();
			int choice;
			System.out.println("Please select what u want to do!!!");
			System.out.println("1. Add Book");
			System.out.println("2. View Book");
			System.out.println("3. Issue book");
			System.out.println("please enter your choice");
			 choice=sc.nextInt();


			switch (choice) {
			case 1:
				objLib.addBook();
				break;
			case 2:
				objLib.viewAllBook();
				break;
			}
		
		}else {
			System.out.println("Please select what u want to do!!!");
			System.out.println("1. Search Book");
			System.out.println("2. View Book");
			System.out.println("3. Return book");
		}
			}
		}
		
			

	}

}
