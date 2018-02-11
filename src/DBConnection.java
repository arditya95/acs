import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static String url = "jdbc:mysql://localhost:3306/db_nfc";    
    private static String driverName = "com.mysql.jdbc.Driver";   
    private static String username = "root";   
    private static String password = "";
    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                // log an exception.
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception.
            System.out.println("Driver not found."); 
        }
        return con;
    }
}
