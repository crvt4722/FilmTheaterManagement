package model;

public class FilmSchedule {
	private int id;
	private Film film;
	private Schedule schedule;
	public FilmSchedule(int id, Film film, Schedule schedule) {
		super();
		this.id = id;
		this.film = film;
		this.schedule = schedule;
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
