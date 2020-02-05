package beeradv.clint;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.Driver;

import beeradv.model.ConnectionClass;

public class ClintSignup extends HttpServlet {

	 
	@Override
	protected void doPost(HttpServletRequest req, 
HttpServletResponse resp) throws ServletException, IOException {
		
       insert(req,resp);
		
	}
	public void insert(HttpServletRequest req,HttpServletResponse resp) {
		
		String query = "insert into clint values(?,?,?)";
		try {
			PrintWriter out = resp.getWriter();
			String un = req.getParameter("un");
			String pwd = req.getParameter("pwd");
			String em = req.getParameter("em");
			
			  Connection con = new ConnectionClass().getConnection();
			  PreparedStatement ps = con.prepareStatement(query);
			  ps.setString(1, un);
			  ps.setString(2, pwd);
			  ps.setString(3, em);
			  int eu = ps.executeUpdate();
			  if (eu==1) {
				out.println("Account Successfully created");
				req.getRequestDispatcher("ClintLogin.html").forward(req, resp);
			} else {
                out.println("Account Creation Unsuccessfull");
			}
			con.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}
	
	  
}
