package dao;

import java.sql.*;
import java.util.*;
import model.*;

public class RoomDAO {
	public RoomDAO() {
		
	}
	
	public ArrayList<Room> getAll(){
		ArrayList<Room> result = new  ArrayList<Room>();
		try {
			Connection conn = RoomSeatDAO.getConnection();
			String select = "SELECT * FROM room";
			PreparedStatement ps = conn.prepareStatement(select);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(new Room(rs.getInt(1), rs.getString(2), rs.getInt(3)));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result; 
	}
	
	public Room getRoom( int id){
		ArrayList<Room> result = new  ArrayList<Room>();
		try {
			Connection conn = RoomSeatDAO.getConnection();
			String select = "SELECT * FROM room WHERE ID = ?";
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Room(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null; 
	}
}
