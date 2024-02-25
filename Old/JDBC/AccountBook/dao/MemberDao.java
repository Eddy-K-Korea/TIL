package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBconnection;

public class MemberDao {
// dao 는 어디서든지 불러오는게 편하다 그래서 싱글톤을 많이 한다.
	
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
	
	public boolean getId(String id) { // 받은 아이디를 파라미터로 넣는다.
		String sql = " SELECT ID "
				+ " FROM MEMBER "
				+ " WHERE ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs =null;
		
		boolean findId = false;
		
		
		try {
			conn = DBconnection.getConnection();  // db 정보 얻는것
			psmt = conn.prepareStatement(sql);	// Query문을 적용
			psmt.setString(1, id);
			
			rs = psmt.executeQuery(); 			// db로부터 데이터를 취득
			
			if(rs.next()) { // true 이면 아이디가 있다
				findId = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Query문이 잘 못 되었습니다.");
		} finally {
			DBClose.dbClose(psmt, conn, rs);
		}
		return findId;
		
	}
	
	public int addMember(String id, String pw, String name, String email) {
		String sql = " INSERT INTO MEMBER(ID,PWD,NAME,EMAIL,AUTH) "
					+ " VALUES(?,?,?,?,3) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBconnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, name);
			psmt.setString(4, email);
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.dbClose(psmt, conn, null);
		}
		return count;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public boolean login(String id, String pw) {
		String sql = " SELECT ID "
				+ " FROM MEMBER "
				+ " WHERE ID = ? AND PWD = ? ";
		
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
