package dao;

import java.sql.*;
import java.util.*;
import model.BillCustomerStat;

public class BillDAO extends BillServiceDAO{
	public BillDAO() {
		super();
	}
	
	public ArrayList<BillCustomerStat> getCustomerRevenueStat(String startTime, String endTime){
		ArrayList<BillCustomerStat> result = new ArrayList<BillCustomerStat>();
		try {
            String select = "SELECT CustomerID , sum(total_amount) as revenue_stat "
            		+ "from bill where payment_time >= ? AND payment_time <= ? "
            		+ " group by customerid "
            		+ "order by revenue_stat desc";
            PreparedStatement ps = this.conn.prepareStatement(select);
            ps.setString(1, startTime);
            ps.setString(2, endTime);
            ResultSet rs = ps.executeQuery();
            String period = startTime + " - " + endTime;
            while(rs.next()) {
            	int customerId = rs.getInt(1);
            	int revenue = rs.getInt(2);
            	result.add(new BillCustomerStat(customerId, revenue, period));
            }
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
