package dao;

import java.sql.*;

public class BillServiceDAO {
	private static Connection conn; 
	private BillServiceDAO() {
		String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bussiness_service_db";
        String user = "root";
        String password = "04072002";        
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(url, user, password);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static Connection getConnection() {
		if (conn == null) new BillServiceDAO();
		return conn;
	}
}
