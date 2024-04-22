package model;

public class User {
	private int id;
	private String fullname, username, password, email, address;
	public User(int id, String fullname, String username, String password, String email, String address) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public String getFullname() {
		return fullname;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getAddress() {
		return address;
	} 
	
	
	
}
