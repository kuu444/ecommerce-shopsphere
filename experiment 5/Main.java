
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/shop_sphere";
        String user = "root";
        String password = "kritika@152006";

        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("MySQL connection successful!");

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            System.out.println(e.getMessage());

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}


