package model;

import java.util.*;
import model.*;
public class Bill {
	private int id, customerId, employeeId;
	private String paymentTime;
	private int totalAmount, bonusPoint;
	private ArrayList<Ticket> tickets;
	private ArrayList<UsedGiftItem> usedGiftItems;
	private ArrayList<UsedSaleItem> usedSaleItems;
	private String ticketReturnStr, usedGiftItemReturnStr, usedSaleItemReturnStr;
	
	public Bill() {
		
	}
	
	public ArrayList<UsedGiftItem> getUsedGiftItems() {
		return usedGiftItems;
	}
	public ArrayList<UsedSaleItem> getUsedSaleItem() {
		return usedSaleItems;
	}
	
	
	
	public Bill(int id, int customerId, int employeeId, String paymentTime, int totalAmount, int bonusPoint,
			ArrayList<Ticket> tickets, ArrayList<UsedGiftItem> usedGiftItems, ArrayList<UsedSaleItem> usedSaleItems) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.employeeId = employeeId;
		this.paymentTime = paymentTime;
		this.totalAmount = totalAmount;
		this.bonusPoint = bonusPoint;
		this.tickets = tickets;
		this.usedGiftItems = usedGiftItems;
		this.usedSaleItems = usedSaleItems;
	}

	public int getId() {
		return id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public String getPaymentTime() {
		return paymentTime;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public int getBonusPoint() {
		return bonusPoint;
	}

	public void setTicketReturnStr() {
		String result = "";
		if (tickets.size() >= 1) {
			for (Ticket x: tickets) {
				if(x.getFilmSchedule()!= null)
					result += x.getFilmSchedule().getFilm().getName() + ", ";
			}		
			
			if(result.length()>1) {
				result = result.trim();
				result = result.substring(0, result.length() - 1);
			}
		}
		this.ticketReturnStr = result;
	}

	public void setUsedGiftItemReturnStr() {
		String result = "";
		if (usedGiftItems.size() >= 1) {
			for (UsedGiftItem x : usedGiftItems) {
				if (x.getGiftItem() != null)
					result += x.getQuantity() + " " + x.getGiftItem().getName() + ", ";
			}
			if(result.length()>1) {
				result = result.trim();
				result = result.substring(0, result.length() - 1);
			}
		}
		this.usedGiftItemReturnStr = result;
	}

	public void setUsedSaleItemReturnStr() {
		String result = "";
		if (usedSaleItems.size() >= 1) {
			for (UsedSaleItem x : usedSaleItems) {
				if (x.getSaleItem() != null)
					result += x.getQuantity() + " " + x.getSaleItem().getName() + ", ";
			}		
			if(result.length()>1) {
				result = result.trim();
				result = result.substring(0, result.length() - 1);
			}
		}
		this.usedSaleItemReturnStr = result;
	}

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public ArrayList<UsedSaleItem> getUsedSaleItems() {
		return usedSaleItems;
	}
	
	public ArrayList<UsedGiftItem> getUsedGiftItem(){
		return usedGiftItems;
	}

	public String getTicketReturnStr() {
		return ticketReturnStr;
	}

	public String getUsedGiftItemReturnStr() {
		return usedGiftItemReturnStr;
	}

	public String getUsedSaleItemReturnStr() {
		return usedSaleItemReturnStr;
	}
	
	
}
