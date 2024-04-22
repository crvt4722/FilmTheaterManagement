package model;

public class Film {
	private int id;
	private String name, category;
	private int duration;
	public Film(int id, String name, String category, int duration) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.duration = duration;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCategory() {
		return category;
	}
	public int getDuration() {
		return duration;
	}
	
	
}
