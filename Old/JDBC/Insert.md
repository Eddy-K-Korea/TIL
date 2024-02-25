# JDBC Insert
## InsertData Method 
```java
public int insertData(String id, String name, int age) { ★ insert, update, delete는 넘어오는 값이 정수(= count)로 넘어온다.
 		
		★ Query -> String 문자열로 되어있다.
    
		String sql = " INSERT INTO USERTEST(ID, NAME, AGE, JOINDATE) "
				+ " VALUES('" + id + "','"+ name + "',"+ age+", SYSDATE)";   ★ 매개변수 넣을 때 " 안에 '를 넣어주어야한다.
				
 		System.out.println("sql: "+ sql);
 		
 		Connection conn = DBConnection.getConnection();
 		Statement stat = null;                           ★ 현재 상태를 확인하는 단계
 		int count = 0;  ★ return 을 해주기 위해서  (몇개가 INSERT되었는지 확인)
		
 		try {
			stat = conn.createStatement();
			
			count = stat.executeUpdate(sql);  ★ executeupdate 는 return 값이 int 이기 때문에 count에 업데이트된 수를 넣어준다.
			
			System.out.println("성공적으로 추가 되었습니다.");
			
		} catch (SQLException e) {        ★ DB가 꺼져있거나 연동 안되어있을경우 예외
			e.printStackTrace();
		}finally {
    
			★ DB를 사용하고 반드시 닫아주어야 한다. 그래서 finally를 통해 무조건 닫는다.
      
			DBClose.close(stat, conn, null);  ★ ResultSet에 대한 부분은 insert에서 초기화하기 않기 때문에 null로 값을 넣어준다.
		}
 		return count;
	}
  ```
  
  ## Inesrt Class
  ```java
  package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBClose;
import db.DBConnection;

public class InsertTest {

	public InsertTest() {
	}
	public int insertData(String id, String name, int age) { 
 		
		String sql = " INSERT INTO USERTEST(ID, NAME, AGE, JOINDATE) "
				+ " VALUES('" + id + "','"+ name + "',"+ age+", SYSDATE)"; 
				
 		System.out.println("sql: "+ sql);
 		
 		Connection conn = DBConnection.getConnection();
 		Statement stat = null;                           
 		int count = 0;  
		
 		try {
			stat = conn.createStatement();
			
			count = stat.executeUpdate(sql);  
			
			System.out.println("성공적으로 추가 되었습니다.");
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}finally {
			DBClose.close(stat, conn, null);
		}
 		return count;
	}
}
```

## Main

```java
package main;

import db.DBConnection;
import jdbc.InsertTest;

public class Main {

	public static void main(String[] args) {
		
		DBConnection.initConnection(); // 생성자에 굳이 넣을 필요없이 바로 불러오기만 하면된다.
		
		InsertTest it = new InsertTest(); 
		int count = it.insertData("abc4", "장영실", 22); // 데이터 insert
		if(count == 1) {
			System.out.println("데이터가"+ count + "개 추가 되었습니다.");
		}
	}
}
```
