package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static void initConnection() {  // static 을 붙이는 이유는 어디서든ㄷ지 바로 접근이 가능하게 끔 한다.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 해당 코드는 클래스가 있는지 확인하는 식

			System.out.println("Driver Loading Success");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()throws Exception {  // Connection (java.sql) = jdbc의 오브젝트를 읽어준다.
		Connection conn = null;
		DBConnection.initConnection();
	
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.7.45:1521:xe", "hr", "hr"); // url 은 연동된 DB의 url  프로퍼티에서 복붙 그리고 사용자, 비번 입력
		
		return conn;  // return 값으로 연결한 인스턴스를 반환한다.
	}
}
