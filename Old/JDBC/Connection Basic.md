# JDBC Connection
## 이클립스 와  DB 연동
1. 이클립스에 DataBase development에 오라클의 주소및 서버가 연동을 시킨것을 기반으로 자바 프로젝트와 연동을 설명
2. JAVA 프로젝트에서 우클릭하면 build path 부분이 있다 configure build path... 의 부분을 들어간다.
3. Libraries 탭으로 들어가서 add external jar을 클릭하여 추가하여준다.

- 기본적인 setting이 끝났다.

## 오라클 드라이브와 연동
- 가장 먼저 연동해야 할 부분은 드라이브 연동이다.
- 드라이브 연동과 DB 서버 연동은 따로 패키지를 만들고 메소드로 진행한다. 
- 또한 static을 설정하여 어디서든지 접근할 수 있게 하면 JDBC 할 수 있다.

```java
public static void initConnection() {                   ★ static 을 붙이는 이유는 어디서든지 바로 접근이 가능하게 끔 한다.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); ★  Class.forName 은 드라이버가 있는지 확인하는 식

			System.out.println("Driver Loading Success");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
```
## DB 서버와 연동
```java
public static Connection getConnection() {     ★ Connection (java.sql) = jdbc의 오브젝트를 읽어준다.
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.7.45:1521:xe", "hr", "hr");  ★ url 은 연동된 DB의 url  프로퍼티에서 복붙 그리고 사용자, 비번 입력
			
			System.out.println("Oracle Connection Success");
			
		} catch (SQLException e) {
			System.out.println("Oracle Connection Fail");
		}
		return conn;  ★ return 값으로 연결한 인스턴스를 반환한다.
	}
```

## DBConnection 객체
```java
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static void initConnection() {  
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 

			System.out.println("Driver Loading Success");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() { 
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.7.45:1521:xe", "hr", "hr"); 
			
			System.out.println("Oracle Connection Success");
			
		} catch (SQLException e) {
			System.out.println("Oracle Connection Fail");
		}
		return conn;  
	}
}
```
### ※ DB 서버는 값을 주고 닫아주어야 한다. 그렇지 않으면 서버에 대한 문제가 발생할 수 있다. 위에 생성한 db 패키지에 close 클래스도 만들어 주고 활용한다.
```java
package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBClose {   ★ 해방시키려는 부분은 클래스로 따로 생성하는 편
	
	public static void close(Statement stmt, Connection conn, ResultSet rs) {
		
		try {
			if(stmt != null) {
				stmt.close();
			}	
			if(conn != null) {
				conn.close();
			}
			if(rs != null) {   ★ Select 절일 때 필요한 클래스
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
```
