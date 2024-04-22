package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.*;
public class SaleItemDAO {
	public SaleItemDAO() {
		
	}
	
	public SaleItem getSaleItem(int id) {
		try {
			Connection conn = BillServiceDAO.getConnection();
			String select = "SELECT * FROM saleitem where ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int price = rs.getInt("price");
				String name = rs.getString("name");
				
				return new SaleItem(id, name, price);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
