package beeradv.clint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beeradv.model.BeerModel;

public class BeerSelector extends HttpServlet {
        
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	       resp.setContentType("text/html");
	       PrintWriter out = resp.getWriter();
	      
	       String color = req.getParameter("color");
	       BeerModel<String> bm = new BeerModel<String>();
	       List<String> brandsl = bm.getBrands(color);
	       
	       req.setAttribute("styles", brandsl);
	       RequestDispatcher rd = 
	    		   req.getRequestDispatcher("BeerRecomendation.jsp");
	       
	      rd.forward(req, resp);
	       
	      
	
	
	
	}
}
