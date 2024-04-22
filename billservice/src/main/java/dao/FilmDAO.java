package dao;
import java.sql.*;
import model.*;
public class FilmDAO {
	public FilmDAO() {
		
	}
	
	public Film getFilm(int id) {
		try {
			Connection conn = BillServiceDAO.getConnection();
			
			String select = "SELECT * FROM film WHERE ID = ?";
			PreparedStatement ps =conn.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String name = rs.getString(2);
				String category = rs.getString(3);
				int duration = rs.getInt(4);
				return new Film(id, name, category, duration);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
