package model;

public class BillCustomerStat {
	private int customerId, revenue;
	private String period;
	public BillCustomerStat(int customerId, int revenue, String period) {
		super();
		this.customerId = customerId;
		this.revenue = revenue;
		this.period = period;
	}
	public int getCustomerId() {
		return customerId;
	}
	public int getRevenue() {
		return revenue;
	}
	public String getPeriod() {
		return period;
	} 
	
}
