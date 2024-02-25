package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.PdsDto;

public class PdsDao {

	private static PdsDao dao = new PdsDao();
	
	private PdsDao() {
	}
	
	public static PdsDao getInstance() {
		return dao;
	}
	
	public List<PdsDto> getPdsList() {
		String sql = " SELECT SEQ, ID, TITLE, CONTENT, FILENAME, READCOUNT, DOWNCOUNT, REGDATE "
					+" FROM PDS "
					+" ORDER BY SEQ DESC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<PdsDto> list = new ArrayList<PdsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getPdsList success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getPdsList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getPdsList success");
			
			while(rs.next()) {
				int i = 1;
				PdsDto dto = new PdsDto(rs.getInt(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getInt(i++), 
										rs.getInt(i++), 
										rs.getString(i++));
				
				list.add(dto);
				System.out.println("4/6 getPdsList success");
			}
			
		} catch (Exception e) {
			System.out.println("getPdsList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	public boolean writePds(PdsDto pds) {
		String sql = " INSERT INTO PDS(SEQ, ID, TITLE, CONTENT, FILENAME, READCOUNT, DOWNCOUNT, REGDATE) "
					+" VALUES(SEQ_PDS.NEXTVAL,?,?,?,?,0,0,SYSDATE) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 writePds success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pds.getId());
			psmt.setString(2, pds.getTitle());
			psmt.setString(3, pds.getContent());
			psmt.setString(4, pds.getFilename());
			System.out.println("2/6 writePds success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 writePds success");
			
		} catch (Exception e) {
			System.out.println("writePds fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count>0?true:false;
	}
	
	public PdsDto getPdsDetail(int seq) {
		String sql = " SELECT SEQ, ID, TITLE, CONTENT, FILENAME, READCOUNT, DOWNCOUNT, REGDATE "
					+" FROM PDS "
					+" WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		PdsDto dto = new PdsDto();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getPdsDetail success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 getPdsDetail success");
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				int i = 1;
				dto = new PdsDto(rs.getInt(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getInt(i++), 
										rs.getInt(i++), 
										rs.getString(i++));
			}
			System.out.println("3/6 getPdsDetail success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}
	
	public boolean updatePds(int seq, String title, String content) {
		String sql = " UPDATE PDS "
					+" SET TITLE = ?, CONTENT = ?, REGDATE = SYSDATE "
					+" WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 updatePds success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, seq);
			System.out.println("2/6 updatePds success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 updatePds success");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count>0?true:false;
	}
	
	public boolean deletePds(int seq) {
		String sql = " DELETE FROM PDS "
					+" WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 deletePds success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 deletePds success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 deletePds success");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count>0?true:false;
	}
}
