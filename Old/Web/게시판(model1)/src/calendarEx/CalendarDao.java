package calendarEx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;

public class CalendarDao {
	private static CalendarDao dao = new CalendarDao();
	
	private CalendarDao() {
	}

	public static CalendarDao getInstance() {
		return dao;
	}
	
	public List<CalendarDto> getCalendarList(String id, String yyyyMM) {
		String sql = " SELECT SEQ, ID, TITLE, CONTENT, RDATE, WDATE " + 
					 " FROM ( SELECT ROW_NUMBER()OVER(PARTITION BY SUBSTR(RDATE, 1 , 8)ORDER BY RDATE ASC) AS RNUM, " + 
					 "			SEQ, ID, TITLE, CONTENT, RDATE, WDATE " + 
					 "	   	  FROM CALENDAR " + 
					 "	   	  WHERE ID=? AND SUBSTR(RDATE, 1, 6)=? ) " + 
					 " WHERE RNUM BETWEEN 1 AND 5 ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		
		List<CalendarDto> list = new ArrayList<CalendarDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getCalendarList success");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, yyyyMM);
			System.out.println("2/6 getCalendarList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getCalendarList success");
			while(rs.next()) {
				int i = 1;
				CalendarDto dto = new CalendarDto(rs.getInt(i++), 
													rs.getString(i++), 
													rs.getString(i++), 
													rs.getString(i++), 
													rs.getString(i++), 
													rs.getString(i++));
				
				list.add(dto);
			}
			System.out.println("4/6 getCalendarList success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	public boolean addCalendar(CalendarDto cal) {
		String sql = " INSERT INTO CALENDAR(SEQ, ID, TITLE, CONTENT, RDATE, WDATE) "
					+" VALUES(SEQ_CAL.NEXTVAL, ?, ? ,? ,?, SYSDATE) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 addCalendar success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cal.getId());
			psmt.setString(2, cal.getTitle());
			psmt.setString(3, cal.getContent());
			psmt.setString(4, cal.getRdate());
			System.out.println("2/6 addCalendar success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 addCalendar success");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count>0?true:false;
	}
	
	public List<CalendarDto> getDateList(String rdate) {
		String sql = " SELECT SEQ, ID, TITLE, CONTENT, RDATE, WDATE "
					+ " FROM CALENDAR "
					+ " WHERE RDATE LIKE ?||'%' "
					+ " ORDER BY WDATE ASC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<CalendarDto> list = new ArrayList<CalendarDto>();
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getDateList success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, rdate);
			System.out.println("2/6 getDateList success");
			
			rs= psmt.executeQuery();
			System.out.println("3/6 getDateList success");
			
			while(rs.next()) {
				int i = 1;
				CalendarDto dto = new CalendarDto(rs.getInt(i++), 
													rs.getString(i++), 
													rs.getString(i++), 
													rs.getString(i++), 
													rs.getString(i++), 
													rs.getString(i++));
				list.add(dto);
			}
			System.out.println("4/6 getDateList success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
	public CalendarDto getCalDetail(int seq) {
		String sql = " SELECT ID, TITLE, CONTENT, RDATE "
					+ " FROM CALENDAR "
					+ " WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		CalendarDto dto = null;
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getCalDetail success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 getCalDetail success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getCalDetail success");
			if(rs.next()) {
				dto = new CalendarDto(rs.getString(1), 
									  rs.getString(2), 
									  rs.getString(3), 
									  rs.getString(4));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}
	
	public boolean deleteCal(int seq) {
		String sql = " DELETE FROM CALENDAR "
					+" WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 deleteCal success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 deleteCal success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 deleteCal success");			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count>0?true:false;
	}
	
	public boolean updateCal(int seq, String title, String content, String rdate) {
		String sql = " UPDATE CALENDAR "
					+" SET TITLE = ? , CONTENT = ?, RDATE = ? "
					+" WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 updateCal success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, rdate);
			psmt.setInt(4, seq);
			System.out.println("2/6 updateCal success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 updateCal success");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count>0?true:false;
	}
}
