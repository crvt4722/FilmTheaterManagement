package model;

public class FilmSchedule {
	private int id;
	private Film film;
	private Schedule schedule;
	private Seat seat; 
	public FilmSchedule(int id, Film film, Schedule schedule, Seat seat) {
		super();
		this.id = id;
		this.film = film;
		this.schedule = schedule;
		this.seat = seat;
	}
	public int getId() {
		return id;
	}
	public Film getFilm() {
		return film;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	
	
}
