import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public  class Items {
    private Connection connection;
    private Scanner scanner;

   public  Items(Connection connection , Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }



    public  void addProducts(){
        String query = "INSERT INTO items(prod_id,prod_name,prod_quantity,prod_price) VALUES(?,?,?,?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            while(true){
                System.out.println("Enter product id: ");
                int prod_id = scanner.nextInt();
                System.out.println("Enter product name: ");
                String prod_name = scanner.next();
                System.out.println("Enter product quantity: ");
                int prod_quantity = scanner.nextInt();
                System.out.println("Enter product price: ");
                double prod_price = scanner.nextDouble();

                scanner.nextLine();
                System.out.println("If you want to add more product type  Y/N: ");
                String choice = scanner.nextLine();

                preparedStatement.setInt(1,prod_id);
                preparedStatement.setString(2,prod_name);
                preparedStatement.setInt(3,prod_quantity);
                preparedStatement.setDouble(4,prod_price);
                preparedStatement.addBatch();

                if(choice.toUpperCase().equals("N")){
                    break;
                }
            }
            int products[] = preparedStatement.executeBatch();
            for(int i=0; i<products.length; i++){
                if(products[i] > 0){
                    System.out.println("PRODUCT ADDED SUCCESFULLY! ");
                }
                else{
                    System.out.println("PRODUCT ADDED FAILED! ");
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public  void viewProduct(){
        String query = "SELECT * FROM items";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int prod_id = resultSet.getInt("prod_id");
                String prod_name = resultSet.getString("prod_name");
                int prod_quantity = resultSet.getInt("prod_quantity");
                double prod_price = resultSet.getDouble("prod_price");

                System.out.println(prod_id + " " + prod_name + " " + prod_quantity + " " + prod_price);

            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void updateProducts(){
       System.out.println("Enter Product Id: ");
       int prod_id = scanner.nextInt();
       System.out.println("Enter Product name: ");
       String prod_name = scanner.next();
       System.out.println("Enter Product quantity: ");
       int prod_quantity = scanner.nextInt();
       System.out.println("Enter Product price: ");
       double prod_price = scanner.nextDouble();
       scanner.nextLine();


        String query = "UPDATE items SET prod_name = ?,prod_quantity = ?,prod_price = ? WHERE prod_id = ?";
       try{
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1,prod_name);
           preparedStatement.setInt(2,prod_quantity);
           preparedStatement.setDouble(3,prod_price);
           preparedStatement.setInt(4,prod_id);

           int rowsAffected = preparedStatement.executeUpdate();
           if(rowsAffected > 0){
               System.out.println("PRODUCT UPDATED");
           }
           else{
               System.out.println("PRODUCT UPDATION FAILED");
           }
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
    }
    public void deleteProducts(){
       System.out.println("Enter Product id");
       int prod_id = scanner.nextInt();
       String query = "DELETE FROM items WHERE prod_id =?";
       try{
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setInt(1,prod_id);

           int rowsAffected = preparedStatement.executeUpdate();
           if(rowsAffected > 0){
               System.out.println("DELETED");
           }
           else{
               System.out.println("NOT DELETED");
           }
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }

    }
}
