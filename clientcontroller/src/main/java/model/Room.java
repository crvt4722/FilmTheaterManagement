package model;

public class Room {
	private int id;
	private String name;
	private int roomSize;
	public Room(int id, String name, int roomSize) {
		super();
		this.id = id;
		this.name = name;
		this.roomSize = roomSize;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getRoomSize() {
		return roomSize;
	}
	
	
}
