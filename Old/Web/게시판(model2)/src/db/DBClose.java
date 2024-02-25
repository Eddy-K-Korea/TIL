package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBClose { // 해방시키려는 부분은 클래스로 따로 생성하는 편
	
	public static void close(PreparedStatement psmt, Connection conn, ResultSet rs) {
		
		try {
			if(psmt != null) {
				psmt.close();
			}	
			if(conn != null) {
				conn.close();
			}
			if(rs != null) {   // Select 절일 때 필요한 클래스
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
