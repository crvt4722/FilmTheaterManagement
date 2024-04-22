package model;

public class CustomerStat extends Customer{
	private int revenue;
	private String period;
	public CustomerStat(int id, String fullname, String username, String password, String email, String address,
			int currentPoint, int revenue, String period) {
		super(id, fullname, username, password, email, address, currentPoint);
		this.revenue = revenue;
		this.period = period;
	}
	public int getRevenue() {
		return revenue;
	}
	public String getPeriod() {
		return period;
	}	
}
