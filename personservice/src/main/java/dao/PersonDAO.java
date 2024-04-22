package dao;

import java.sql.*;

public class PersonDAO {
	protected Connection conn; 
	public PersonDAO() {
		String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/person_service_db";
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
