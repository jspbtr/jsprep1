package jspiders.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspiders.dbo.DbConnection;

public class StudentSignup extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 ServletContext sct = getServletContext(); 
		 String dburl =
		 sct.getInitParameter("dburl"); 
		 String dbname =
		 sct.getInitParameter("dbname"); 
		 String user = sct.getInitParameter("user");
		 String password = sct.getInitParameter("password");
	
	    Connection con = 
	DbConnection.getConnection(dburl, dbname, user, password);
	
	}
     public void signup(Connection con,HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
    	 PrintWriter o = resp.getWriter();
    	 String sname = req.getParameter("studentname");
    	 String semail = req.getParameter("username");
    	 String spassword = req.getParameter("password");
    	 
    	 String query = "insert into sdata values(?,?,?)";
    	 
    	 PreparedStatement ps = con.prepareStatement(query);
         ps.setString(1, semail);
         ps.setString(2, spassword);
         ps.setString(3, sname);
         
         int eu = ps.executeUpdate();
         System.out.println(eu);
         if (eu!=0) {
			o.println("signup successfull");
		}else {
			o.print("signup unsuccessfull");
		}
         
         
     
     
     
     
     }
}









