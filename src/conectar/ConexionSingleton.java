package conectar;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConexionSingleton {
	
	private static Connection conn = null;
//	private String driver;
//	private String url;
//	private String usuario;
//	private String password;
//	

	
	
	private ConexionSingleton() throws NamingException {
//		 url = "jdbc:oracle:thin:@localhost:1521:xe"; 
//		 driver = "oracle.jdbc.driver.OracleDriver";  
//		 usuario = "seguritoproject";                            
//		 password = "seguritoproject";
		 
		    Context ctx = new InitialContext();
		    Context env = (Context) ctx.lookup("java:comp/env");
		   String url = (String) env.lookup("jdbcURL");
		   String driver = (String) env.lookup("jdbcDriver");
		   String usuario = (String) env.lookup("jdbcUsername");
		   String password = (String) env.lookup("jdbcPassword");
		 
		 try {
			 Class.forName(driver);
			 conn = DriverManager.getConnection(url,usuario,password);

		 }
		 catch(ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
	}
	
	public static Connection getConnection() throws SQLException {
		if (conn == null || conn.isClosed()) {
			try {
				new ConexionSingleton();
			} catch (NamingException e) {
				e.printStackTrace();
			}			
		}
		return conn;
	}
	
	}