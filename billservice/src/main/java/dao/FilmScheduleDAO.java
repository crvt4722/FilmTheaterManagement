package dao;

import model.*;
import java.sql.*;
import java.util.*;

public class FilmScheduleDAO {
	public FilmScheduleDAO() {
		
	}
	
	public FilmSchedule getFilmSchedule (int id) {
		try {
			Connection conn = BillServiceDAO.getConnection();
			String select = "SELECT * FROM filmschedule WHERE ID = ?";
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				FilmDAO filmDAO = new FilmDAO();
				Film film = filmDAO.getFilm(rs.getInt(3));				
				return new FilmSchedule(id, film, null, null);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
