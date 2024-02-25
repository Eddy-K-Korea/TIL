package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBconnection;

public class MemberDao {

	private static MemberDao dao = null;
	
	private String loginId;
	
	private MemberDao() {
	}
	
	public static MemberDao getInstance() {
		if(dao == null) {
			dao = new MemberDao();
		}
		return dao;
	}
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public int addMember(String id, String pw, String name, int age) {
		String sql = " INSERT INTO COFMEMBER(ID,PW,NAME,AGE) "
					+ " VALUES(?,?,?,?) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBconnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, name);
			psmt.setInt(4, age);
			
			count = psmt.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("쿼리가 잘못 되었습니다.");
		} finally {
			DBClose.dbClose(psmt, conn, null);
		}
		return count;
	}
	
	public boolean getId(String id) {
		String sql = " SELECT ID "
				+ " FROM COFMEMBER "
				+ " WHERE ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		boolean findId = false;
		
		try {
			conn = DBconnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				findId = true;
			}
		} catch (SQLException e) {
			System.out.println("쿼리가 잘못 되었습니다.");
		} finally {
			DBClose.dbClose(psmt, conn, rs);
		}
		return findId;
	}
	
	public boolean login(String id, String pw) {
		String sql = " SELECT ID "
				+ " FROM COFMEMBER "
				+ " WHERE ID = ? AND PW = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		boolean login = false;
		
		conn = DBconnection.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				login = true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.dbClose(psmt, conn, rs);
		}
		return login;
	}
}
