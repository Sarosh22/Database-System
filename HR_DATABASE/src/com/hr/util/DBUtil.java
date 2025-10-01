package com.hr.util;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	public static Connection conn;
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String user = "HR";
	private static final String pass = "oracle";
 
	public static Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pass);
 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
		
		
		return conn;
	}
 
	public static ResultSet executeQuery(String sql) throws Exception {
		
		
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery(sql);

	
		return rs;
		
 
	}
	
	public static void executeUpdate(String sql) throws Exception {
		Statement st = getConnection().createStatement();
		st.executeUpdate(sql);
		st.close();
	}
 
}
