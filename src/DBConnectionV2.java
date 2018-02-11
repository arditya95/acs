import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionV2 {

  public static void main(String[] argv) {
	System.out.println("-------- MySQL JDBC Connection Testing ------------");

	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("MySQL JDBC Driver Not Found");
		System.out.println("Error to find : " + e.getMessage());
//		e.printStackTrace();
		return;
	}

	System.out.println("MySQL JDBC Driver Registered!");
	Connection connection = null;

	try {
		connection = DriverManager
		.getConnection("jdbc:mysql://localhost:3306/db_nfc","root", "");

	} catch (SQLException e) {
		System.out.println("Connection Failed! Check output console");
	    // handle any errors
	    System.out.println("SQLException: " + e.getMessage());
//	    System.out.println("SQLState: " + e.getSQLState());
//	    System.out.println("VendorError: " + e.getErrorCode());
//		e.printStackTrace();
		return;
	}

	if (connection != null) {
		System.out.println("Successful to connect!");
	} else {
		System.out.println("Failed to make connection!");
	}
  }
}