package beeradv.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class ConnectionClass {
        
	
	   public Connection getConnection() {
		   Connection con = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
					+ "beeradv?user=root&password=root");
			
			
		} catch (Exception e) {
			System.out.println("Problem Establishing with Database");
			e.printStackTrace();
		}
		   
		return con;
		   
	   }
}
