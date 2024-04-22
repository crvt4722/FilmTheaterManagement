package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.*;
public class CustomerDAO extends PersonDAO{

	public CustomerDAO() {
		super();
	}
	
	public Customer getCustomer(int id) {		
		try {
            String select = "SELECT * FROM User, Customer WHERE ID = ? AND UserID = ?";
            PreparedStatement ps = this.conn.prepareStatement(select);
            ps.setInt(1, id);
            ps.setInt(2, id);
            
            ResultSet rs = ps.executeQuery();            
            while(rs.next()) {            	
            	String fullname = rs.getString(2);
            	String username = rs.getString(3);
            	String password = rs.getString(4);
            	String email = rs.getString(5);
            	String address = rs.getString(6);
            	int currentPoint = rs.getInt(7);
            	
            	return new Customer(id, fullname, username, password, email, address, currentPoint);
            }
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean exchangeGiftItem(int id, int usedPoint) {
		try {
			String update = "UPDATE customer SET current_point = current_point - ? WHERE UserID = ? ";
            PreparedStatement ps = this.conn.prepareStatement(update);
            ps.setInt(1, usedPoint);
            ps.setInt(2, id);
            return ps.execute();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
