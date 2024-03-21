import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
public class ImageInsert {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydatabaseFnds";
        String userName = "root";
        String password = "Akanth24@";

        String image_path = "D:\\Projects\\Java Projects\\Java JDBC\\images\\vennela.jpg";
        String query = "insert into image_table(image_data) values (?);";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully!");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url,userName,password);

            FileInputStream fileInputStream = new FileInputStream(image_path);
            byte[] image_data = new byte[fileInputStream.available()];
            fileInputStream.read(image_data);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setBytes(1,image_data);

            int rowEffected = preparedStatement.executeUpdate();

            if(rowEffected>0){
                System.out.println("Inserted Successfully!!, "+rowEffected+" row(s) effected");
            }else{
                System.out.println("Insertion failed!!!");
            }

            preparedStatement.close();;
            con.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
