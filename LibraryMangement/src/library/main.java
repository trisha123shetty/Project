package library;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DatabaseConnection objDatabase = new DatabaseConnection();

        try {
            objDatabase.DatabaseConnect();
        } catch (ClassNotFoundException e) {
            System.out.println("Database Connection Error: " + e.getMessage());
            return;
        }

        login(sc);
        sc.close();
    }

    public static void login(Scanner sc) {
        System.out.println("Welcome to the Library System!");
        System.out.println("Please enter your login details.");

        Member objMember = new Member();
        objMember.InsertMember();  // Ensure this method inserts new users correctly

        System.out.print("Enter Password: ");
        String password = sc.next();

        String memberName = objMember.getName();

        if (objMember.isAdmin(memberName, password)) {
            System.out.println("Welcome, Admin " + memberName);
            adminMenu(sc);
        } else {
            System.out.println("Welcome, " + memberName);
            userMenu(sc);
        }
    }

    public static void adminMenu(Scanner sc) {
        Library objLib = new Library();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. View Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Logout");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    objLib.addBook();
                    break;
                case 2:
                    objLib.viewAllBook();
                    break;
                case 3:
                    objLib.deleteBook();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    login(sc);
                    return;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void userMenu(Scanner sc) {
        Library objLib = new Library();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nUser Menu:");
            System.out.println("1. Search Book");
            System.out.println("2. View Books");
            System.out.println("3. Buy Book");
            System.out.println("4. Return Book");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    objLib.SearchBook();
                    break;
                case 2:
                    objLib.UserViewBook();
                    break;
                case 3:
                    objLib.issueBook();
                    break;
                case 4:
                    objLib.returnBook();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    login(sc);
                    return;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
