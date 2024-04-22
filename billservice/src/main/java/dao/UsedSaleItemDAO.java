package dao;
import model.*;
import java.util.*;
import java.sql.*;

public class UsedSaleItemDAO {
	public UsedSaleItemDAO() {
		
	}
	
	public ArrayList<UsedSaleItem> getUsedSaleItemByBill(int billId){
		ArrayList<UsedSaleItem> result = new ArrayList<UsedSaleItem>();
		
		try {
			Connection conn = BillServiceDAO.getConnection();
			String select = "SELECT * FROM usedsaleitem WHERE BillID = ?";
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, billId);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SaleItemDAO saleItemDAO = new SaleItemDAO();
				SaleItem saleItem = saleItemDAO.getSaleItem(rs.getInt(4));
				int id = rs.getInt(1);
				int quantity = rs.getInt(2);
				int price = rs.getInt(3);  
				
				result.add(new UsedSaleItem(id, saleItem, quantity, price));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
