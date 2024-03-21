import com.mysql.cj.util.StringInspector;

import java.io.*;
import java.sql.*;

public class ImageRetrieval {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydatabaseFnds";
        String userName = "root";
        String password = "Akanth24@";

        String image_destination_path = "D:\\Projects\\Java Projects\\Java JDBC\\ConvertedToPNG\\";
        String query = "select image_data from image_table where image_id=?;";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, userName, password);

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, 4);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                byte[] image_data = rs.getBytes("image_data");
                String image_path = image_destination_path+"vennelaNew.png";
                FileOutputStream fileOutputStream = new FileOutputStream(image_path);
                fileOutputStream.write(image_data);
                System.out.println("Image Converted into PNG successfully!!");
            }else{
                System.out.println("Image not found!!");
            }
            preparedStatement.close();
            ;
            con.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


