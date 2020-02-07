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

public class GenerateHallticket extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletContext sct = getServletContext();
		String dburl = sct.getInitParameter("dburl");
		String dbname = sct.getInitParameter("dbname");
		String user = sct.getInitParameter("user");
		String password = sct.getInitParameter("password");

		Connection con = DbConnection.getConnection(dburl, dbname, user, password);
		try {
			ghc(con, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ghc(Connection con, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		PrintWriter out = resp.getWriter();
		PreparedStatement ps = con.prepareStatement("select semail from src order by semail asc");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String email = rs.getString("semail");
			int hcno = 0;
			for (int i = 0; i < email.length(); i++) {
				hcno = hcno + email.charAt(i);
			}
			ps = con.prepareStatement("insert into hc values(?,?)");
			ps.setString(1, email);
			ps.setInt(2, hcno*6);
			int eu = ps.executeUpdate();
			if (eu != 0) {
				out.print("Halltickets successfully generated");
			} else {
				out.print("Halltickets generation unsuccessful");
			}
		}

	}
}
