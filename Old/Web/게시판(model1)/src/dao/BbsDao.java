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
	
	public BbsDto getBbsdto(int seq) {
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
	public void readcount(int seq) {
		String sql = " UPDATE BBS "
					+" SET READCOUNT = (SELECT READCOUNT+1 FROM BBS WHERE SEQ = ?) "
					+" WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 readcount success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			psmt.setInt(2, seq);
			System.out.println("2/6 readcount success");
			
			psmt.executeUpdate();
			System.out.println("3/6 readcount success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
	}
	public boolean answer(int seq, BbsDto bbs) {
		// update
		String sql1 = " UPDATE BBS "
					// 새로운 댓글을 넣기위해 그 아래 댓글들을 하나씩 밑으로 밀어준다
					+ " SET STEP = STEP + 1 "
					//1. 현재 시퀸스(REF)가 같은 조건 필터
					+ " WHERE REF = (SELECT REF FROM BBS WHERE SEQ = ?) "	
					//2. 그 시퀸스 번호와 같은 데이터보다 큰 수들은 step을 밑으로 밀어야 하기 때문에 step이 큰 애들을 찾는다
					+ " AND STEP > (SELECT STEP FROM BBS WHERE SEQ = ?) ";	
		
		// insert
		String sql2 = " INSERT INTO BBS "
					+ " (SEQ,ID, "
					+ " REF, STEP, DEPTH, "
					+ " TITLE, CONTENT, WDATE, DEL, READCOUNT) "
					+ " VALUES(SEQ_BBS.NEXTVAL, ?, "
					+ "  (SELECT REF FROM BBS WHERE SEQ = ?), "	// 같은 그룹 번호 그대로 적용
					//같은 시퀸스(그룹)에 아래 글을 달아야 하기 때문에 STEP에 +1
					+ "	 (SELECT STEP FROM BBS WHERE SEQ = ?)+1, " 
					//같은 시퀸스(그룹)에서 아래 한쪽 안쪽에 글이 달려야 하기 때문에 DEPTH +1
					+ "  (SELECT DEPTH FROM BBS WHERE SEQ = ?)+1, "
					+ "  ?, ?, SYSDATE, 0, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			System.out.println("1/6 answer success");
			
			//update
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, seq);
			psmt.setInt(2, seq);
			System.out.println("2/6 answer success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 answer success");
			
			// psmt초기화
			psmt.clearParameters();	// 이전 sql1을 초기화
			
			// insert
			psmt = conn.prepareStatement(sql2);
			psmt.setString(1, bbs.getId());
			psmt.setInt(2, seq);
			psmt.setInt(3, seq);
			psmt.setInt(4, seq);
			psmt.setString(5, bbs.getTitle());
			psmt.setString(6, bbs.getContent());
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
			}
			DBClose.close(psmt, conn, null);
			System.out.println("6/6 answer success");
		}
		return count>0?true:false;
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
	
	public List<BbsDto> getSearchid(String search) {
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT "
				+" FROM BBS "
				+" WHERE ID = ? AND DEL = 0 "
				+" ORDER BY REF DESC, STEP ASC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getSearchid success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, search);
			System.out.println("2/6 getSearchid success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getSearchid success");
			
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
			System.out.println("4/6 getSearchid success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	public List<BbsDto> getSearchtitle(String search) {
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT "
				+" FROM BBS "
				+" WHERE TITLE LIKE'%'||?||'%' AND DEL = 0 "
				+" ORDER BY REF DESC, STEP ASC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getSearchid success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, search);
			System.out.println("2/6 getSearchid success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getSearchid success");
			
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
			System.out.println("4/6 getSearchid success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	public List<BbsDto> getSearchcontent(String search) {
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT "
				+" FROM BBS "
				+" WHERE CONTENT LIKE'%'||?||'%' AND DEL = 0 "
				+" ORDER BY REF DESC, STEP ASC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getSearchid success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, search);
			System.out.println("2/6 getSearchid success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getSearchid success");
			
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
			System.out.println("4/6 getSearchid success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	// 검색의 방법 1
	public List<BbsDto> getBbsList(String choice, String searchword) {
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT "
					+" FROM BBS "
					+" WHERE 1=1 ";
		String sqlWord ="";
		if(choice.equals("title")) {
			sqlWord = " AND TITLE LIKE '%"+ searchword.trim() + "%' ";
		}else if(choice.equals("writer")) {
			sqlWord = " AND ID='"+ searchword.trim()+"' ";
		}else if(choice.equals("content")) {
			sqlWord = " AND CONTENT LIKE '%"+ searchword.trim() + "%' ";
		}
		sql = sql+ sqlWord;
		sql	+= " ORDER BY REF DESC, STEP ASC ";
		
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
	
	public boolean updateContent(String nwtitle, String nwcont,int seq) {
		String sql = " UPDATE BBS "
					+" SET TITLE = ? , CONTENT = ? , WDATE = SYSDATE "
					+" WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 updateContent success");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, nwtitle);
			psmt.setString(2, nwcont);
			psmt.setInt(3, seq);
			System.out.println("2/6 updateContent success");
			
			count = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count>0?true:false;
	}
	
	// 글의 모든 갯수
	public int getAllBbs(String choice, String searchword) {
		String sql = " SELECT COUNT(*) FROM BBS ";
		String sqlword = "";
		if(choice.equals("id")) {
			sqlword = " WHERE ID ='"+searchword.trim()+"' ";
		}else if(choice.equals("title")) {
			sqlword = " WHERE TITLE LIKE '%"+ searchword.trim() + "%' ";
		}else if(choice.equals("content")) {
			sqlword = " WHERE CONTENT LIKE '%"+ searchword.trim() + "%' ";
		}
		sql = sql+ sqlword;
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
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return len;
		
	}
	
	public List<BbsDto> getBbspagingList(String choice, String searchword, int page) {
		/*
			1. row 번호
			2. 검색
			3. 정렬
			4. 범위
		*/
		
		
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT "
				+" FROM ";
		sql += " (SELECT ROW_NUMBER()OVER(ORDER BY REF DESC, STEP ASC) AS RNUM, " +
				" SEQ, ID, REF,STEP,DEPTH,TITLE,CONTENT,WDATE,DEL,READCOUNT " +
					 " FROM BBS ";
		String sqlWord ="";
		if(choice.equals("title")) {
			sqlWord = " WHERE TITLE LIKE '%"+ searchword.trim() + "%' AND DEL = 0 ";
		}else if(choice.equals("id")) {
			sqlWord = " WHERE ID='"+ searchword.trim()+"' AND DEL = 0 ";
		}else if(choice.equals("content")) {
			sqlWord = " WHERE CONTENT LIKE '%"+ searchword.trim() + "%' AND DEL = 0 ";
		}
		sql = sql+ sqlWord;			  
		sql	+= " ORDER BY REF DESC, STEP ASC) ";
		sql	+= " WHERE RNUM >= ? AND RNUM <= ? ";
		
		int start, end;
		start = 1 + 7*page;
		end = 7 + 7*page;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBbsList success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
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
}








