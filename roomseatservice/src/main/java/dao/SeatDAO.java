package dao;

import java.sql.*;
import java.util.*;
import model.*;

public class SeatDAO {
	public SeatDAO() {
		
	}
	
	
	public ArrayList<Seat> getAll( int roomId){
		ArrayList<Seat> result = new  ArrayList<Seat>();
		try {
			Connection conn = RoomSeatDAO.getConnection();
			String select = "SELECT * FROM seat WHERE RoomID = ?";
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, roomId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {				
				result.add(new Seat(rs.getInt(1), rs.getInt(2), rs.getString(3), null));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result; 
	}
	
	public Seat getSeat( int id){		
		try {
			Connection conn = RoomSeatDAO.getConnection();
			String select = "SELECT * FROM seat WHERE ID = ?";
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Seat(rs.getInt(1), rs.getInt(2), rs.getString(3), null);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean updateSeat(Seat seat) {
		try {
			Connection conn = RoomSeatDAO.getConnection();
			String update = "UPDATE seat set position = ?, type = ? WHERE id = ? ";
			PreparedStatement ps = conn.prepareStatement(update);
			
			ps.setInt(1, seat.getPosition());
			ps.setString(2, seat.getType());
			ps.setInt(3, seat.getId());
			
			return ps.execute();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
