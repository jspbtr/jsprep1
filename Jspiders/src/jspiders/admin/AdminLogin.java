package jspiders.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspiders.dbo.DbConnection;

public class AdminLogin extends HttpServlet {

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
			  login(con, req, resp); 
			} catch (SQLException e) { 
		 e.printStackTrace(); }
		 

	}

	public void login(Connection con, HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException {
		PrintWriter o = resp.getWriter();
		String email = req.getParameter("username");
		o.println(email);
		String password = req.getParameter("password");
		o.println(password);
		String query = "select apassword from adata where aemail=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, email);
		ResultSet eq = ps.executeQuery();
		if (eq.next()) {
			String apassword = eq.getString("apassword");
			if (apassword.equals(password)) {
				o.println("Login success");
			} else {
				o.println("Invalid password");
			}
		} else {
			o.println("Invalid email");
		}
		con.close();
	}

}
