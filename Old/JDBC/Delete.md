# JDBC Delete
## Delete Class
```java
package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;

public class DeleteTest {

	public boolean deleteTest(String id) {
		
		String sql = " DELETE FROM USERTEST "
				+ " WHERE ID = ? ";       ★ 이전 insert 와 update에서 사용한 ' ' 가 아닌 ? 를 사용할 수 있다.
		
		System.out.println("sql: "+sql);
		
		Connection conn = DBConnection.getConnection();
		PreparedStatement psmt = null;  ★ WHERE 절에서 ? 를 사용할시 preparedStatement를 사용해야한다.
		
		int count = 0;
		
		try {
			psmt = conn.prepareStatement(sql);   ★ 쿼리문을 넣어주고 여기서 쿼리문이 완성된다.
			psmt.setString(1, id);               ★ ? 부분의 컬럼과 들어갈 값이 무엇인지 set 해준다. (1번째 컬럼(ID), id (String id)파라미터로 받은 값을 대입)
			count = psmt.executeUpdate();  
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count > 0? true:false;
	}
}
```

## Main
```java
package main;

import db.DBConnection;
import jdbc.DeleteTest;

public class Main {

	public static void main(String[] args) {
		DBConnection.initConnection();
		
		DeleteTest dt = new DeleteTest();
		
		String id = "abc4";   ★ 삭제할 ID 
		boolean b = dt.deleteTest(id);
		
		if(b == true) {
			System.out.println("정상적으로 삭제되었습니다.");
		}
	}
}
```
