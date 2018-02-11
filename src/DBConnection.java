import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DBConnection{  
	public static void main(String args[]){  
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
		}
		catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver Not Found");
			System.out.println("Error to find : " + e.getMessage());
//			e.printStackTrace();
			return;
		}
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_nfc","root",""); //koneksi ke database value(jdbc:mysql://localhost/nama_database","username","password")
			
			//TEST//
			Statement stmt = con.createStatement(); //deklarasi statement
			ResultSet rs = stmt.executeQuery("select * from tb_mhs");  //contoh execute query
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  //print Hasil
			//TEST END//
			
			con.close();  //menutup koneksi
		}	
		catch(SQLException e){ 
		    // handle any errors
		    System.out.println("SQLException: " + e.getMessage());
//		    System.out.println("SQLState: " + e.getSQLState());
//		    System.out.println("VendorError: " + e.getErrorCode());
//			e.printStackTrace();
			return;
			}  
	}  
}  