package dao;

import java.sql.*;

public class BillServiceDAO {
	protected Connection conn; 
	public BillServiceDAO() {
		String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bill_service_db";
        String user = "root";
        String password = "04072002";        
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(url, user, password);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
