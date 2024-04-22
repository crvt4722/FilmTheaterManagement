package dao;

import java.sql.*;
import java.util.*;
import model.*;

public class BillDAO{
	public BillDAO() {		
	}
	
	public ArrayList<BillCustomerStat> getCustomerRevenueStat(String startTime, String endTime){
		ArrayList<BillCustomerStat> result = new ArrayList<BillCustomerStat>();
		try {
			Connection conn = BillServiceDAO.getConnection();
            String select = "SELECT CustomerID , sum(total_amount) as revenue_stat "
            		+ "from bill where payment_time >= ? AND payment_time <= ? "
            		+ " group by customerid "
            		+ "order by revenue_stat desc";
            PreparedStatement ps = conn.prepareStatement(select);
            ps.setString(1, startTime);
            ps.setString(2, endTime);
            ResultSet rs = ps.executeQuery();
            String period = startTime + "_" + endTime;
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
	
	public ArrayList<Bill> getBill(int customerId, String startTime, String endTime){
		ArrayList<Bill> result = new ArrayList<Bill>();
		try {
			Connection conn = BillServiceDAO.getConnection();
            String select = "SELECT * FROM Bill WHERE CustomerID = ? AND payment_time >= ? AND payment_time <= ?";
            PreparedStatement ps = conn.prepareStatement(select);
            ps.setInt(1, customerId);
            ps.setString(2, startTime);
            ps.setString(3, endTime);
            ResultSet rs = ps.executeQuery();            
            while(rs.next()) {
            	int id = rs.getInt(1);
            	int employeeId = rs.getInt(3);
            	String paymentTime = rs.getString(4);
            	int totalAmount = rs.getInt(5);
            	int bonusPoint = rs.getInt(6);
            	
            	TicketDAO ticketDAO = new TicketDAO();
            	ArrayList<Ticket> tickets = ticketDAO.getTicketByBill(id);
            	
            	UsedGiftItemDAO usedGiftItemDAO = new UsedGiftItemDAO();
            	ArrayList<UsedGiftItem> usedGiftItems = usedGiftItemDAO.getUsedGiftItemByBill(id);
            	
            	UsedSaleItemDAO usedSaleItemDAO = new UsedSaleItemDAO();
            	ArrayList<UsedSaleItem> usedSaleItems = usedSaleItemDAO.getUsedSaleItemByBill(id);
            	
            	Bill bill = new Bill(id, customerId, employeeId, paymentTime, totalAmount, bonusPoint, tickets, usedGiftItems, usedSaleItems);
            	bill.setTicketReturnStr();
            	bill.setUsedGiftItemReturnStr();
            	bill.setUsedSaleItemReturnStr();
            	
            	result.add(bill);
            }
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public int getBillId(String paymentTime, int customerId) {
		try {
			Connection conn = BillServiceDAO.getConnection();
			String select = "SELECT ID FROM bill where CustomerID = ? AND payment_time = ?";
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, customerId);
			ps.setString(2, paymentTime);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
		
	public boolean add(Bill bill) {
		try {
			Connection conn = BillServiceDAO.getConnection();
			String insert = "INSERT INTO bill(CustomerID, EmployeeID, payment_time, total_amount, bonus_point) VALUES (?, 0, ?, 0, ?)";
			PreparedStatement ps = conn.prepareStatement(insert);
			
			ps.setInt(1, bill.getCustomerId());
			ps.setString(2, bill.getPaymentTime());
			ps.setInt(3, bill.getBonusPoint());
			
			ps.execute();
			
			int billId = this.getBillId(bill.getPaymentTime(), bill.getCustomerId());
			
			System.out.println(billId);
			
			UsedGiftItemDAO usedGiftItemDAO = new UsedGiftItemDAO();
			for (int i=0; i<bill.getUsedGiftItem().size()/2; ++i) {
				usedGiftItemDAO.add(bill.getUsedGiftItem().get(i), billId);
			}
			return true; 
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
