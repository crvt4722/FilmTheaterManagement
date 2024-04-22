package model;

public class Employee extends User{
	private String role;

	public Employee(int id, String fullname, String username, String password, String email, String address,
			String role) {
		super(id, fullname, username, password, email, address);
		this.role = role;
	}

	public String getRole() {
		return role;
	}
	
}
