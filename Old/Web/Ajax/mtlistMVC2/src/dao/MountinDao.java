package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Db.DataBaseMt;
import dto.MountinDto;

public class MountinDao {

	private static MountinDao dao = new MountinDao();
	
	private MountinDao() {
		DataBaseMt.initConnection();
	}
	public static MountinDao getInstance() {
		return dao;
	}
	public List<MountinDto> getMountList() {
		String sql = " SELECT LOCATION, NAME, HEIGHT, MANAGE "
					+" FROM MOUNTLIST ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<MountinDto> list = new ArrayList<MountinDto>();
		
		try {
			conn = DataBaseMt.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String location = rs.getString("location");
				String name = rs.getString("name");
				double height = rs.getDouble("height");
				String manage = rs.getString("manage");
				
				MountinDto dto = new MountinDto(location, name, height, manage);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseMt.close(conn, psmt, rs);
		}
		return list;
		
	}
	
	public List<MountinDto> getsearchList(String loc) {
		String sql = " SELECT LOCATION, NAME, HEIGHT, MANAGE "
				+" FROM MOUNTLIST "
				+" WHERE LOCATION = ? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<MountinDto> list = new ArrayList<MountinDto>();
		
		try {
			conn = DataBaseMt.getConnection();
			psmt= conn.prepareStatement(sql);
			psmt.setString(1, loc);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String location = rs.getString("location");
				String name = rs.getString("name");
				double height = rs.getDouble("height");
				String manage = rs.getString("manage");
				
				MountinDto dto = new MountinDto(location, name, height, manage);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseMt.close(conn, psmt, rs);
		}
		return list;
	}
	public List<MountinDto> getsearchList2(String mtname) {
		String sql = " SELECT LOCATION, NAME, HEIGHT, MANAGE "
				+" FROM MOUNTLIST "
				+" WHERE NAME = ? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<MountinDto> list = new ArrayList<MountinDto>();
		
		try {
			conn = DataBaseMt.getConnection();
			psmt= conn.prepareStatement(sql);
			psmt.setString(1, mtname);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String location = rs.getString("location");
				String name = rs.getString("name");
				double height = rs.getDouble("height");
				String manage = rs.getString("manage");
				
				MountinDto dto = new MountinDto(location, name, height, manage);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseMt.close(conn, psmt, rs);
		}
		return list;
	}
	public List<MountinDto> getsearchList3(String mana) {
		String sql = " SELECT LOCATION, NAME, HEIGHT, MANAGE "
				+" FROM MOUNTLIST "
				+" WHERE MANAGE LIKE'%'||?||'%' ";	//like 쓸때 % 는 ||로 합해준다
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<MountinDto> list = new ArrayList<MountinDto>();
		
		try {
			conn = DataBaseMt.getConnection();
			psmt= conn.prepareStatement(sql);
			psmt.setString(1, mana);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String location = rs.getString("location");
				String name = rs.getString("name");
				double height = rs.getDouble("height");
				String manage = rs.getString("manage");
				
				MountinDto dto = new MountinDto(location, name, height, manage);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseMt.close(conn, psmt, rs);
		}
		return list;
	}
}
