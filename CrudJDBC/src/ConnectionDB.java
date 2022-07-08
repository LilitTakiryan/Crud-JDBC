import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
    public Connection connect() {

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connected");
        return con;
    }

}
