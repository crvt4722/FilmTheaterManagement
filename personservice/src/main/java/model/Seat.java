package model;

public class Seat {
	private int id, position;
	private String type;
	private Room room;
	
	public Seat(int id, int position, String type, Room room) {
		super();
		this.id = id;
		this.position = position;
		this.type = type;
		this.room = room;
	}

	public int getId() {
		return id;
	}

	public int getPosition() {
		return position;
	}

	public String getType() {
		return type;
	}

	public Room getRoom() {
		return room;
	} 
	
}
