# JDBC Update
## Update Class
- Inesrt와 비슷한 방식으로 구현되며 쿼리문만 update로 바뀐다.
- 메소드를 boolean형식으로 return되도록 하였다. 이유는 메인 클래스에서 확인

```java
package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBClose;
import db.DBConnection;

public class UpdateTest {

	public boolean update(String id, int age) {
		
		String sql = " UPDATE USERTEST "
				   + " SET AGE = " + age + " "
				   + " WHERE ID = '"+ id + "' ";
		System.out.println("sql : "+sql);
		
		Connection conn = DBConnection.getConnection();
		Statement stmt = null;          ★ 쿼리문을 실행시켜서 결과를 가져오는 목적을 가진 소스코드
		
		int count = 0;
		
		try {
			stmt = conn.createStatement();
			count = stmt.executeUpdate(sql);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(stmt, conn, null);
		}
		
		return count > 0 ? true:false; ★  삼항연산자로 리턴값 반환가능 , count가 0 이상이면 stmt가 1개이상 값을 count로 주었기 때문에  true가 나옴
		
	}
}
```
## Main
- 우선 update할 id를 지정하고 변경할 age 값도 지정한 후 update 파라미터로 넣어준다.
- return값은 True, false 이기 때문에 boolean b 를 잡아주고 조건절을 통해 정상적으로 수정 되었는지 확인 할 수 있다.
```java
package main;

import db.DBConnection;
import jdbc.UpdateTest;

public class Main {

	public static void main(String[] args) {

		DBConnection.initConnection(); // DB 동작 준비 끝
		
		UpdateTest ut = new UpdateTest();
		
		String id = "abc3";
		
		int age = 45;
		
		boolean b = ut.update(id, age);
		if(b == true) {
			System.out.println("정상적으로 수정 되었습니다.");
		}
	}
}
```
