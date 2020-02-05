package jspiders.dbo;

import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.cj.jdbc.Driver;

public class DbConnection {
	private static Connection con = null;   
	private DbConnection() {	
	}
	   public static Connection getConnection(
		String dburl,String dbname,String user
		,String password) {
		   String url = dburl+dbname;
		  if (con==null) {
			  try {
			Driver driver = new Driver();
			DriverManager.deregisterDriver(driver);
			con = DriverManager.
					getConnection(url, user, password);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return con;	   
	   }
}
