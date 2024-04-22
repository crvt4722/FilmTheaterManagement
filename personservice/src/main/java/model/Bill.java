package model;

public class Bill {
	private int id, customerId, employeeId;
	private String paymentTime;
	private int totalAmount, bonusPoint;
	
	public Bill() {
		
	}
	public Bill(int id, int customerId, int employeeId, String paymentTime, int totalAmount, int bonusPoint) {		
		this.id = id;
		this.customerId = customerId;
		this.employeeId = employeeId;
		this.paymentTime = paymentTime;
		this.totalAmount = totalAmount;
		this.bonusPoint = bonusPoint;
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
	
	
}
