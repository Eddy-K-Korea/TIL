package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBconnection;
import dto.AbookDto;

public class AbookDao {

	private static AbookDao adao= null;
	
	private AbookDao() {
	}
	
	public static AbookDao getInstance() {
		if(adao == null) {
			adao = new AbookDao();
		}
		return adao;
	}
	
	
	public int insertData(AbookDto dto) {
		String sql = " INSERT INTO ACCOUNTBOOK(SEQ,ID,IO_KIND,AMOUNT,CONTENT,WDATE) "
				+ "	VALUES(SEQ_MEMBER_ID.NEXTVAL,?,?,?,?,SYSDATE) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		
		try {
			conn = DBconnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getIo_kind());
			psmt.setInt(3, dto.getAmount());
			psmt.setString(4, dto.getContent());
						
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("쿼리가 잘 못 되었습니다.");
		} finally {
			DBClose.dbClose(psmt, conn, null);
		}
		return count;
	}
	
	public AbookDto kindSearch(String id,String sh) {
		String sql = " SELECT ID, IO_KIND, AMOUNT, CONTENT, WDATE "
				+ " FROM ACCOUNTBOOK  "
				+ " WHERE ID = '"+id+"' AND CONTENT LIKE '%"+sh+"%' ";
		
		Connection conn = DBconnection.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		AbookDto dto = null;
		
		
		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				String _id = rs.getString("ID");
				String _IO_KIND = rs.getString("IO_KIND");
				int _amount = rs.getInt("AMOUNT");
				String _content = rs.getString("CONTENT");
				String _wdate = rs.getString("WDATE");
				
				dto = new AbookDto(_id, _IO_KIND, _amount, _content, _wdate);
			}
			
			
		} catch (SQLException e) {
			System.out.println("쿼리가 잘못 되었습니다.");
		} finally {
			DBClose.dbClose(stmt, conn, rs);
		}
		return dto;
	}
	public List<AbookDto> dateContent(String id,String date1) {
		String sql = " SELECT ID, IO_KIND, AMOUNT, CONTENT, WDATE "
				+ " FROM ACCOUNTBOOK "
				+ " WHERE ID = '"+id+"' AND WDATE BETWEEN TO_DATE('"+date1+"','YYYYMMDD') AND TO_DATE('"+date2+"','YYYYMMDD') ";
		
		Connection conn = DBconnection.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		AbookDto dto = null;
		
		List<AbookDto> list = new ArrayList<AbookDto>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String _id = rs.getString("ID");
				String io_kind = rs.getString("IO_KIND");
				int amount = rs.getInt("AMOUNT");
				String content = rs.getString("CONTENT");
				String wdate = rs.getString("WDATE");
				
				dto = new AbookDto(_id, io_kind, amount, content, wdate);
				
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("쿼리가 잘 못되었습니다.");
		} finally {
			DBClose.dbClose(stmt, conn, rs);
		}
		return list;
	}
}
