package com.bit.parking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParkingController {
	


	public long calculatePrice() {
		ParkingDTO car  = new ParkingDTO();
		long total;
		total = (((car.getOutTime()-car.getInTime())/60000)/1) * 2000+2000;
		return total;
	}
	
	public int insertCar(ParkingDTO dto) {
		String sql = " INSERT INTO PARKINGCAR(CARNUM,INTIME,LONGCLIENT) "
				+ " VALUES(?,SYSDATE,?) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = ParkingDB.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getCarNo());
			psmt.setInt(2, dto.getTicket());
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("쿼리문이 잘 못 되었습니다");
		} finally {
			ParkingDB.close(psmt, conn, null);
		}
		return count;
	}
	
	public ParkingDTO getCarInfo(String carNum) {
		String sql = " SELECT CARNUM, LONGCLIENT "
				+ " FROM PARKINGCAR "
				+ " WHERE CARNUM = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ParkingDTO dto = null;
		
		try {
			conn = ParkingDB.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, carNum);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String carNumber = rs.getString("CARNUM");
				int tic  = rs.getInt("LONGCLIENT");
				
				dto = new ParkingDTO(carNumber, tic);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ParkingDB.close(psmt, conn, rs);
		}
		return dto;
	}

}
