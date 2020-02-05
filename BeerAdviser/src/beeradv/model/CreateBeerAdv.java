package beeradv.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateBeerAdv extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Connection con = new ConnectionClass().getConnection();
		try {
			PreparedStatement ps = 
		con.prepareStatement("insert into adv values(?,?,?)");
			ps.setString(1, req.getParameter("color"));
			ps.setString(2, req.getParameter("brand1"));
			ps.setString(3, req.getParameter("brand2"));
			int eu = ps.executeUpdate();
			if (eu==1) {
			   out.print("<h1>BeerAdv Updation Successfull<h1>");
			} else {
				 out.print("<h1>BeerAdv Updation Unsuccessfull<h1>");
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Problem with data insertion");
			e.printStackTrace();
		}
		
		
	}

}
