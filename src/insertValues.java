import java.sql.*;
public class insertValues {
    public static void main(String[] args) throws ClassNotFoundException,SQLException{

        String url = "jdbc:mysql://localhost:3306/myDatabaseFnds";
        String userId = "root";
        String pass = "Akanth24@";
        String queryU = "INSERT INTO Friends (id,name,age,address,phone_no) values (4,'Vennela',21,'Rajahmundry','7095457677')";
        String queryR = "Select * from Friends";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection con = DriverManager.getConnection(url,userId,pass);
            Statement smt = con.createStatement();

            int rowsEffected = smt.executeUpdate(queryU);
            if(rowsEffected>0){
                System.out.println("Inserted successfully!!, "+rowsEffected + "row(s) effected ");
            }else{
                System.out.println("Insert failed!!");
            }

            ResultSet rs = smt.executeQuery(queryR);

            while(rs.next()){
                int id  = rs.getInt("id");
                String name  = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                String phone_no = rs.getString("phone_no");

                System.out.println("............................................");
                System.out.println(id + "  " +name+"  "+age+"  "+address+"  "+phone_no);
            }
            System.out.println("............................................");

            rs.close();
            smt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }




        System.out.println("connection closed!!!");
    }
}
