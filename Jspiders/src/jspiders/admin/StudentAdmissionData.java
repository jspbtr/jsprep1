package jspiders.admin;

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

public class StudentAdmissionData extends HttpServlet {

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
	try {
		addsrc(con, req, resp);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	}
     public void addsrc(Connection con,HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
    	 PrintWriter o = resp.getWriter();
    	 String semail = req.getParameter("username");
    	 String sname = req.getParameter("name");
    	 
    	 String query = "insert into src values(?,?)";
    	 
    	 PreparedStatement ps = con.prepareStatement(query);
         ps.setString(1, semail);
         ps.setString(2, sname);
         
         int eu = ps.executeUpdate();
         System.out.println(eu);
         if (eu!=0) {
			req.getRequestDispatcher("adminhome.html").forward(req, resp);
		}else {
			o.print("signup unsuccessfull");
		}
}
}
