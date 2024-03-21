import java.sql.*;

public class TransactionControl {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/myDatabaseFnds";
        String userId = "root";
        String pass = "Akanth24@";
        String withDrawlQuery = "UPDATE banking_table set amount = amount - ? where account_number = ? ;";
        String depositQuery = "UPDATE banking_table set amount = amount + ? where account_number = ? ;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection con = DriverManager.getConnection(url, userId, pass);
            con.setAutoCommit(false);

            PreparedStatement withDrawlStatement = con.prepareStatement(withDrawlQuery);
            withDrawlStatement.setDouble(1,500.00);
            withDrawlStatement.setString(2,"jagguAcc");

            PreparedStatement depositStatement = con.prepareStatement(depositQuery);
            depositStatement.setDouble(1,500.00);
            depositStatement.setString(2,"akanthAcc");

            int withdraw = withDrawlStatement.executeUpdate();
            int deposit = depositStatement.executeUpdate();

            if(withdraw>0 && deposit>0){
                con.commit();
                System.out.println("transaction successful!!");
            }else{
                con.rollback();
                System.out.println("Transaction failed!!");
            }

            withDrawlStatement.close();
            depositStatement.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("connection closed!!!");
    }
}
