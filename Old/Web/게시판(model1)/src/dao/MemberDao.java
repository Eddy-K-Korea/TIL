package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

public class MemberDao {

	private static MemberDao dao = new MemberDao();
	
	private MemberDao() {
		DBConnection.initConnection();
	}
	
	public static MemberDao getInstance() {
		return dao;
	}
	
	public boolean getId(String id) {
		// id 확인 ->  true/false
		
		String sql = " SELECT ID "
					+" FROM MEMBER "
					+" WHERE ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		boolean findId = false;
		
		try {
			try {
				conn = DBConnection.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			
			rs= psmt.executeQuery();
			
			if(rs.next()) {
				findId = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return findId;
	}
	
	public boolean addMember(MemberDto dto) {
		// 회원가입의 데이터 -> DB
		String sql = " INSERT INTO MEMBER(ID,PWD,NAME,EMAIL,AUTH) "
					+" VALUES(?, ?, ?, ?, 3) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			try {
				conn = DBConnection.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count > 0 ? true : false;
	}
	
	public MemberDto login(String id, String pwd) {
		String sql = " SELECT ID, NAME, EMAIL, AUTH "
					+" FROM MEMBER "
					+" WHERE ID = ? AND PWD = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 login success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pwd);
			System.out.println("2/6 login success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 login success");
			
			if(rs.next()) {
				String uesr_id = rs.getString(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				int auth = rs.getInt(4);
				
				dto = new MemberDto(uesr_id, null, name, email, auth);
			}
			System.out.println("4/6 login success");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}
}
