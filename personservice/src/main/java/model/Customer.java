package model;

public class Customer extends User{
	private int currentPoint;

	public Customer(int id, String fullname, String username, String password, String email, String address,
			int currentPoint) {
		super(id, fullname, username, password, email, address);
		this.currentPoint = currentPoint;
	}

	public int getCurrentPoint() {
		return currentPoint;
	}
			
	
}
