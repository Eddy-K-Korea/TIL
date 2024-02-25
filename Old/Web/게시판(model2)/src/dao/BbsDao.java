package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.BbsDto;

public class BbsDao {

	private static BbsDao dao = new BbsDao();
	
	private BbsDao() {
		DBConnection.initConnection();
	}
	
	public static BbsDao getInstance() {
		return dao;
	} 
	
	public List<BbsDto> getBbsList() {
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT "
					+" FROM BBS "
					+" ORDER BY REF DESC, STEP ASC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBbsList success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getBbsList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getBbsList success");
			
			while(rs.next()) {
				int i = 1;
				
				BbsDto dto = new BbsDto(rs.getInt(i++), 
										rs.getString(i++), 
										rs.getInt(i++), 
										rs.getInt(i++), 
										rs.getInt(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getInt(i++), 
										rs.getInt(i++));
				list.add(dto);
				
			}
			System.out.println("4/6 getBbsList success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	public boolean writeBbs(BbsDto dto) {
		String sql =" INSERT INTO BBS(SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT) "
					+" VALUES(SEQ_BBS.NEXTVAL, ?, (SELECT NVL(MAX(REF),0)+1 FROM BBS), " //SEQ는 1 그리고 서브쿼리부터 찾는다, 근데 null값이 나오기 때문에 0 이 출력, +1 해줌
								+" 0, 0, ?, ?, SYSDATE, 0, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 writeBbs success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			System.out.println("2/6 writeBbs success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 writeBbs success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count>0?true:false;
	}
	
	public BbsDto getBbs(int seq) {
		String sql = " SELECT * "
					+" FROM BBS "
					+" WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		BbsDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBbsdto success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 getBbsdto success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getBbsdto success");
			
			if(rs.next()) {
				int i = 1;
				dto = new BbsDto();
				dto.setSeq(rs.getInt(i++));
				dto.setId(rs.getString(i++));
				dto.setRef(rs.getInt(i++));
				dto.setStep(rs.getInt(i++));
				dto.setDepth(rs.getInt(i++));
				dto.setTitle(rs.getString(i++));
				dto.setContent(rs.getString(i++));
				dto.setWdate(rs.getString(i++));
				dto.setDel(rs.getInt(i++));
				dto.setReadcount(rs.getInt(i++));
			}
			System.out.println("4/6 getBbsdto success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}
	public boolean bbsDelete(int seq) {
		String sql = " UPDATE BBS "
					+" SET DEL = 1 "
					+" WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn= DBConnection.getConnection();
			System.out.println("1/6 bbsDelete success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 bbsDelete success");
			
			count = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count>0?true:false;
	}
	
	public boolean answer(int seq, BbsDto dto) {
		String sql1 = " UPDATE BBS "
					+" SET STEP = STEP+1 "
					+" WHERE REF = (SELECT REF FROM BBS WHERE SEQ = ?) "
					+" AND STEP > (SELECT STEP FROM BBS WHERE SEQ = ?) ";
		
		String sql2 = " INSERT INTO BBS(SEQ,ID,REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT) "
					+ " VALUES(SEQ_BBS.NEXTVAL, ?, (SELECT REF FROM BBS WHERE SEQ = ?), (SELECT STEP FROM BBS WHERE SEQ = ?)+1,(SELECT DEPTH FROM BBS WHERE SEQ = ?)+1,?,?,SYSDATE,0,0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			System.out.println("1/6 answer success");
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, seq);
			psmt.setInt(2, seq);
			System.out.println("2/6 answer success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 answer success");
			
			psmt.clearParameters();
			
			psmt = conn.prepareStatement(sql2);
			psmt.setString(1, dto.getId());
			psmt.setInt(2, seq);
			psmt.setInt(3, seq);
			psmt.setInt(4, seq);
			psmt.setString(5, dto.getTitle());
			psmt.setString(6, dto.getContent());
			System.out.println("4/6 answer success");
			
			count = psmt.executeUpdate();
			System.out.println("5/6 answer success");
			
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}DBClose.close(psmt, conn, null);
			System.out.println("6/6 answer success");
		}
		return count>0?true:false;
	}
	
	public List<BbsDto> getSearchBbs(String choice, String search, int page) {
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT "
				+" FROM ";
		sql += " (SELECT ROW_NUMBER()OVER(ORDER BY REF DESC, STEP ASC) AS RNUM, " + //from 의 서브 쿼리 앞에 row넘버를 붙여서 정렬
				" SEQ, ID, REF,STEP,DEPTH,TITLE,CONTENT,WDATE,DEL,READCOUNT " +
					 " FROM BBS ";
		String sqlWord ="";
		if(choice.equals("title")) {  // 검색어가 있을경우 아래 조건문을 통해 WHERE 조건 추가
			sqlWord = " WHERE TITLE LIKE '%"+ search.trim() + "%' AND DEL = 0 ";
		}else if(choice.equals("id")) {
			sqlWord = " WHERE ID='"+ search.trim()+"' AND DEL = 0 ";
		}else if(choice.equals("content")) {
			sqlWord = " WHERE CONTENT LIKE '%"+ search.trim() + "%' AND DEL = 0 ";
		}
		sql = sql+ sqlWord;			  
		sql	+= " ORDER BY REF DESC, STEP ASC) ";
		sql	+= " WHERE RNUM >= ? AND RNUM <= ? ";
		
		int start,end;
		start = 1+5*page;
		end = 5+5*page;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getSearchBbs success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/6 getSearchBbs success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getSearchBbs success");
			
			while(rs.next()) {
				int i = 1;
				
				BbsDto dto = new BbsDto(rs.getInt(i++), 
										rs.getString(i++), 
										rs.getInt(i++), 
										rs.getInt(i++), 
										rs.getInt(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getInt(i++), 
										rs.getInt(i++));
				list.add(dto);
			}
			System.out.println("4/6 getSearchBbs success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	public int getAllBbs(String choice, String search) {
		String sql = " SELECT COUNT(*) FROM BBS ";
		String sqlword = "";
		if(choice.equals("id")) {
			sqlword = " WHERE ID = '"+search.trim()+"' ";
		}else if(choice.equals("title")) {
			sqlword = " WHERE TITLE LIKE '%"+ search.trim() + "%' ";
		}else if(choice.equals("content")) {
			sqlword = " WHERE CONTENT LIKE '%"+ search.trim() + "%' ";
		}
		
		sql = sql + sqlword;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int len = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getAllBbs success");
			psmt= conn.prepareStatement(sql);
			System.out.println("2/6 getAllBbs success");
			rs = psmt.executeQuery();
			if(rs.next()) {
				len = rs.getInt(1);
			}
			System.out.println("3/6 getAllBbs success");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return len;
	}
	
}








