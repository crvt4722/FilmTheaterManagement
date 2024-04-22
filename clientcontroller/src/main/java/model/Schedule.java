package model;

public class Schedule {
	private int id;
	private String startTime;
	public Schedule(int id, String startTime) {
		super();
		this.id = id;
		this.startTime = startTime;
	}
	public int getId() {
		return id;
	}
	public String getStartTime() {
		return startTime;
	}
	
	
}
