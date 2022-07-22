package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class MySQLDriver {
	
    private final String DB_URL = "jdbc:mysql://localhost:3307/database";
    private final String USER = "root";
    private final String PASS = "";
    
    private static MySQLDriver instance;
    
    public static MySQLDriver getInstance(){
        if(instance == null) instance = new MySQLDriver();
        return instance;
    }
    
    public Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("hhh");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLDriver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }
    public static void main(String[] args) {
		MySQLDriver.getInstance().getConnection();
	}
}