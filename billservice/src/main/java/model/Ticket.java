package model;

public class Ticket {
	private int id;
	private FilmSchedule filmSchedule;
	private Seat seat;
	private int price;
	public Ticket(int id, FilmSchedule filmSchedule, Seat seat, int price) {
		super();
		this.id = id;
		this.filmSchedule = filmSchedule;
		this.seat = seat;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public FilmSchedule getFilmSchedule() {
		return filmSchedule;
	}
	public Seat getSeat() {
		return seat;
	}
	public int getPrice() {
		return price;
	} 
	
}
