package model;

public class GiftItem {
	private int id;
	private String name;
	private int neededPoint;
	private String description; 
	private int quantity;
	
	public GiftItem(int id, String name, int neededPoint, String description, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.neededPoint = neededPoint;
		this.quantity = quantity;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getNeededPoint() {
		return neededPoint;
	}

	public String getDescription() {
		return description;
	}

	public int getQuantity() {
		return quantity;
	}
	
}
