package model;

public class SaleItem {
	private int id;
	private String name;
	private int price;
	private String description; 
	
	public SaleItem (int id, String name, int price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}
}
