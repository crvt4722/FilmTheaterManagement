package dao;
import model.*;
import java.util.*;
import java.sql.*;

public class UsedGiftItemDAO {
	public UsedGiftItemDAO() {
		
	}
	
	public ArrayList<UsedGiftItem> getUsedGiftItemByBill(int billId){
		ArrayList<UsedGiftItem> result = new ArrayList<UsedGiftItem>();
		
		try {
			Connection conn = BillServiceDAO.getConnection();
			String select = "SELECT * FROM usedgiftitem WHERE BillID = ?";
			
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, billId);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GiftItemDAO GiftItemDAO = new GiftItemDAO();
				GiftItem GiftItem = GiftItemDAO.getGiftItem(rs.getInt(3));
				int id = rs.getInt(1);
				int quantity = rs.getInt(2);				
				
				
				result.add(new UsedGiftItem(id, GiftItem, quantity));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	public boolean add (UsedGiftItem usedGiftItem, int BillId) {
		try {
			Connection conn = BillServiceDAO.getConnection();
			String insert = "INSERT INTO usedgiftitem(quantity, GiftItemID, BillID) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(insert);
			
			System.out.println(usedGiftItem.getGiftItem().getId());
			
			ps.setInt(1, usedGiftItem.getQuantity());
			ps.setInt(2, usedGiftItem.getGiftItem().getId());
			ps.setInt(3, BillId);
			return ps.execute();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
