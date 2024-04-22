package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.*;
public class CustomerStatDAO extends PersonDAO{

	public CustomerStatDAO() {
		super();
	}
	
	public CustomerStat getCustomerStat(int id, int revenue, String period) {		
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
            	            	
            	return new CustomerStat(id, fullname, username, password, email, address, currentPoint, revenue, period);            	
            }
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
