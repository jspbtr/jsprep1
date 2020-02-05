package beeradv.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeerModel<E> {

	public List<String> getBrands(String color) {
		List<String> brands = new ArrayList<String>();
		Connection con = new ConnectionClass().getConnection();
		
			try {
	PreparedStatement ps = 
	con.prepareStatement("Select brand1,brand2 from adv where color = ?");
	ps.setString(1, color);
	ResultSet eq = ps.executeQuery();
	while(eq.next()) {
		brands.add( eq.getString("brand1"));
		brands.add(eq.getString("brand2"));
	}	con.close();
			} catch (Exception e) {
				System.out.println("Trouble fetching data from database");
				e.printStackTrace();
			}

		return brands;
	}
	
}
