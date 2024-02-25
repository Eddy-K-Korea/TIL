package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBconnection;
import dto.OrderDto;

public class OrderDao {

	private static OrderDao dao = null;
	
	private OrderDao() {
	}
	
	public static OrderDao getInstance() {
		if(dao == null) {
			dao = new OrderDao();
		}
		return dao;
	}
	
	public int insertOrder(OrderDto dto) {
		String sql = " INSERT INTO ORDERCOFFEE(SEQ,ID,COFFEE,C_SIZE,SYRUP,OTHER,COUNT_C) "
				+ "	VALUES(ORDER_SEQ.NEXTVAL,?,?,?,?,?,?) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBconnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getCoffee());
			psmt.setString(3, dto.getC_size());
			psmt.setString(4, dto.getSyrup());
			psmt.setString(5, dto.getOther());
			psmt.setInt(6, dto.getCount());
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("쿼리문이 잘못되었습니다.");
		} finally {
			DBClose.dbClose(psmt, conn, null);
		}
		return count;
	}
	public List<OrderDto> contentData(String id) {
		String sql = " SELECT COFFEE, C_SIZE, SYRUP, OTHER, COUNT_C "
				+ " FROM ORDERCOFFEE "
				+ " WHERE ID = ? "
				+ " ORDER BY SEQ DESC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		OrderDto dto = null;
		List<OrderDto> list = new ArrayList<OrderDto>();
		
		try {
			conn = DBconnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String coffee = rs.getString("COFFEE");
				String c_size = rs.getString("C_SIZE");
				String syrup = rs.getString("SYRUP");
				String other = rs.getString("OTHER");
				int count_c = rs.getInt("COUNT_C");
				
				dto = new OrderDto(id, coffee, c_size, syrup, other, count_c);
				list.add(dto);
			}
			
			
		} catch (SQLException e) {
			System.out.println("쿼리가 잘못 되었습니다.");
		} finally {
			DBClose.dbClose(psmt, conn, rs);
		}
		return list;
		
	}
	
	public int deleteData(String id) {
		String sql = " DELETE FROM ORDERCOFFEE "
				+ " WHERE ID = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn= DBconnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("쿼리가 잘못되었습니다.");
		} finally {
			DBClose.dbClose(psmt, conn, null);
		}
		return count;
	}
	
	
	
}
