package com.bit.parking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ParkingDB {

	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.30.1.1:1521:xe", "hr", "hr");
			System.out.println("Oracle Connection Success");
		} catch (SQLException e) {
			System.out.println("Oracle Connection Fail");
		}
		return conn;
	}
	
	public static void close(Statement stmt, Connection conn, ResultSet rs) {
		
		try {
			if(stmt != null) {
				stmt.close();
			}	
			if(conn != null) {
				conn.close();
			}
			if(rs != null) {   
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
