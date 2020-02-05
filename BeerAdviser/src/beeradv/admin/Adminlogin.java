package beeradv.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beeradv.model.ConnectionClass;

public class Adminlogin extends HttpServlet {
     
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String un = req.getParameter("un");
		String pwd = req.getParameter("pwd");
		Connection con = new ConnectionClass().getConnection();
		try {
			PreparedStatement ps = 
con.prepareStatement("select password from admin where username = ?");
			ps.setString(1, un);
			ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
					String dbpwd = rs.getString("password");
					if (dbpwd.equals(pwd)) {
						
						req.getRequestDispatcher("CreateBeerAdv.html").forward(req, resp);
					}else {
						
						out.println("Invalid Password");
					}
				} else {
	                
						out.println("Invalid Username");
				}
				con.close();
		} catch (Exception e) {
			System.out.println("unnable to login");
			e.printStackTrace();
		}
	}
	
}
