import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class testingInputStream {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\AKANT\\OneDrive\\Pictures\\Demo.txt";

        FileInputStream fi = new FileInputStream(path);
        byte [] data = new byte[fi.available()];
        fi.read(data);
        String new_path = "C:\\Users\\AKANT\\OneDrive\\Pictures\\";
        String des_path = new_path+"demoResult.txt";
        FileOutputStream fo = new FileOutputStream(des_path);
        fo.write(data);
    }
}
