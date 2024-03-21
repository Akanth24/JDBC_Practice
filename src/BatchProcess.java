import java.sql.*;
import java.util.Scanner;

public class BatchProcess {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/myDatabaseFnds";
        String userId = "root";
        String pass = "Akanth24@";
        String query = "insert into banking_table(account_number,amount) values(?,?);";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection con = DriverManager.getConnection(url, userId, pass);
            con.setAutoCommit(false);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("Enter the bank details.");
                System.out.print("Account Number :");
                String account_number = sc.nextLine();
                System.out.print("Amount :");
                double amount = sc.nextDouble();
                sc.nextLine();
                preparedStatement.setString(1, account_number);
                preparedStatement.setDouble(2, amount);

                preparedStatement.addBatch();
                System.out.println("Do you want to add more Accounts : (Y/N)");
                String decision = sc.nextLine();
                if(decision.toUpperCase().equals("N")){
                    break;
                }
            }

            int [] effectedQueriesArray = preparedStatement.executeBatch();
            con.commit();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

