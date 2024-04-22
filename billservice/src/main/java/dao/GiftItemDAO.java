package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import model.*;
public class GiftItemDAO {
	public GiftItemDAO() {
		
	}
	
	public ArrayList<GiftItem> getAll(){
		ArrayList<GiftItem> result = new ArrayList<GiftItem>();
		try {
			Connection conn = BillServiceDAO.getConnection();
			String select = "SELECT * FROM giftitem";
			
			PreparedStatement ps = conn.prepareStatement(select);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				int neededPoint = rs.getInt(2);
				String name = rs.getString("name");
				String description = rs.getString(4);
				
				result.add(new GiftItem(id, name, neededPoint,description, 0)); 
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	public GiftItem getGiftItem(int id) {
		try {
			Connection conn = BillServiceDAO.getConnection();
			String select = "SELECT * FROM saleitem where ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int price = rs.getInt("price");
				String name = rs.getString("name");
				
				return new GiftItem(id, name, price, "", 0);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
