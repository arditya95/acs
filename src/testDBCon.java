import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class testDBCon {
	public static void main(String args[]) throws SQLException{  
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		try {
			ResultSet rs = stmt.executeQuery("select * from tb_mh");
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  //print Hasil
		}
		catch(SQLException e){ 
		    // handle any errors
		    System.out.println("SQLException: " + e.getMessage());
			}
	}
}

