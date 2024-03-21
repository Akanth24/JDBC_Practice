import java.sql.*;

public class UsingPreparedStatements {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/myDatabaseFnds";
        String userId = "root";
        String pass = "Akanth24@";
        String query = "Select*from Friends where id= ?;";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection con = DriverManager.getConnection(url, userId, pass);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,2);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                String phone_no = rs.getString("phone_no");

                System.out.println("............................................");
                System.out.println(id + "  " + name + "  " + age + "  " + address + "  " + phone_no);
            }
            System.out.println("............................................");

            rs.close();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("connection closed!!!");
    }
}