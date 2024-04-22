package model;

public class GiftItem {
	private int id;
	private String name;
	private int neededPoint;
	private String description; 
	
	public GiftItem(int id, String name, int neededPoint) {
		super();
		this.id = id;
		this.name = name;
		this.neededPoint = neededPoint;
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
}
