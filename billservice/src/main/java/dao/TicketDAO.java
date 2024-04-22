package dao;
import model.*;
import java.util.*;
import java.sql.*;

public class TicketDAO {
	public TicketDAO() {
		
	}
	
	public ArrayList<Ticket> getTicketByBill(int billId){
		ArrayList<Ticket> result = new ArrayList<Ticket>();
		
		try {
			Connection conn = BillServiceDAO.getConnection();
			String select = "SELECT * FROM ticket WHERE BillID = ?";
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, billId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				int price = rs.getInt(2);
				FilmScheduleDAO filmScheduleDAO = new FilmScheduleDAO();
				FilmSchedule filmSchedule = filmScheduleDAO.getFilmSchedule(rs.getInt(4));
				
				result.add(new Ticket(id, filmSchedule, null, price));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
