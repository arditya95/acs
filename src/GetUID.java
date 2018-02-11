import java.util.List;
import java.math.BigInteger;
import javax.smartcardio.*;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class GetUID {
	
	static String bin2hex(byte[] data) {
	    return String.format("%0" + (data.length * 2) + "X", new BigInteger(1,data));
	}
	
 public static void main(String[] args) {
  try {
	        
   // Display the list of terminals
   TerminalFactory factory = TerminalFactory.getDefault();
   List<CardTerminal> terminals = factory.terminals().list();
   System.out.println("Terminals: " + terminals);
 
   // Use the first terminal
   CardTerminal terminal = terminals.get(0);
 
   // Connect wit hthe card
   Card card = terminal.connect("*");
   System.out.println("Card: " + card);
   CardChannel channel = card.getBasicChannel();
 
   // Send test command
   ResponseAPDU response = channel.transmit(new CommandAPDU( new byte[] { (byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00, (byte) 0x05 }));
   System.out.println("Response: " + response.toString());
   
   if (response.getSW1() == 0x63 && response.getSW2() == 0x00)  System.out.println("Failed");
   
   //bin to hex to string
   String uid = bin2hex(response.getData()).toString();
   
   System.out.println("UID: " + bin2hex(response.getData()));
   
   //Hashing uid with SHA-256

    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(uid.getBytes());

    byte byteData[] = md.digest();

    //convert the byte to hex format
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < byteData.length; i++) {
     sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    }
    System.out.println("Final Hashing : " + sb.toString());
    
    String hash_uid = sb.toString();
    
	Connection con = DBConnection.getConnection();
	Statement stmt = con.createStatement();
	try {
		int rs = stmt.executeUpdate("INSERT INTO tb_mhs (nfc_mhs) value ('"+hash_uid+"')");
	}
	catch(SQLException e){ 
	    // handle any errors
	    System.out.println("SQLException: " + e.getMessage());
		}
   
   // Disconnect the card
   card.disconnect(false);
 
  } catch(Exception e) {
 
   System.out.println("Ouch: " + e.toString());
 
  }
 }
}