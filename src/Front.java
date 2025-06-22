import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Front {
    private static final String url = "jdbc:mysql://localhost:3306/products";
    private static final String username = "root";
    private static final String password = "Peice@129";

    public static void main(String args[]){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Scanner scanner = new Scanner(System.in);

            Items items = new Items(connection,scanner);

            while (true) {
                System.out.println("\n=== PRODUCT MENU ===");
                System.out.println("1. Add Products");
                System.out.println("2. View Products");
                System.out.println("3. Update Products");
                System.out.println("4. Delete Products");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        items.addProducts();
                        break;
                    case 2:
                        items.viewProduct();
                        break;
                    case 3:
                        items.updateProducts();
                        break;
                    case 4:
                        items.deleteProducts();
                        break;
                    case 5:
                        System.out.println("Exiting GoodBye");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }



    }
}
