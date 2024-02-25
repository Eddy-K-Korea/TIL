# JDBC Select
## Dto 생성
- 데이터에 저장 되어있는 값을 받아와 명시하기 위해서 dto를 생성해주어야 한다.
- 현재 예제에는 상관이 없지만 Web에서 DB와 java를 연동해서 사용할 경우 dto에 직렬화를 해주어야한다.<br>
(해당 부분은 네트워크의 TCP 에서 객체를 송/수신할때 한번 언급하였다.)
```java
package dto;

import java.io.Serializable;

public class UserDto implements Serializable {  // 직렬화를 해주어야 한다. 서버로 통해서 데이터를 오고 가기때문데 (웹에서)
	
	private String id;
	private String name;
	private int age;
	private String Joindate;
	
	public UserDto() {
	}
	
	public UserDto(String id, String name, int age, String joindate) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		Joindate = joindate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getJoindate() {
		return Joindate;
	}

	public void setJoindate(String joindate) {
		Joindate = joindate;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", age=" + age + ", Joindate=" + Joindate + "]";
	}
	
}
```
## Select Class
### 1. 단일 데이터를 검색할때
- statement 사용
```java
public UserDto search(String id) {
		
		String sql = " SELECT ID, NAME, AGE, JOINDATE "
				+ " FROM USERTEST "
				+ " WHERE ID = '"+ id + "' ";
		
		System.out.println("sql: "+ sql);
		
		Connection conn = DBConnection.getConnection();
		Statement stmt = null;
		
		ResultSet rs = null;    ★ DB로 부터 결과를 return
		
		UserDto dto = null;
		
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {                      ★ 찾는 데이터가 존재 하는 경우 true  없으면 false
				String _id = rs.getString("id");   ★ getString(" DB 생성시 만들었던 컬럼명을 넣어준다.")
				String _name = rs.getString("name"); 
				int _age = rs.getInt("age");
				String _joindate = rs.getString("joindate");
				
				dto = new UserDto(_id, _name, _age, _joindate); ★ dto에 받은 값을 넣어주고 객체를 생성  
			}
			★ 만약 값이 없어서 그냥 넘어오게 되면 값은 null이다.
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(stmt, conn, rs);
		}
		return dto;
	}
  ```
  - PreparedStatement
  ```java
  public UserDto select(String id) {
		
		String sql = " SELECT ID, NAME, AGE, JOINDATE "
					+ " FROM USERTEST "
					+ " WHERE ID = ? AND AGE = ? "; ★ 예를 들기위해 두가지 조건을 걸었다.
		
		Connection conn = DBConnection.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		UserDto dto = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);   ★  Query에서  ? 부분에 해당. ''의 생략이 가능하다 . 자동적으로 붙는다. 이 시점에서 Query 문이 완성된다.
			psmt.setInt(2, 25); ★ 두번째 age의 조건이며 age컬럼은 2번째이고, 값은 직접 넣어주어도 되고, 파라미터로 id처럼 받아서 값을 넣어도 된다.
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new UserDto();
				
				dto.setId(rs.getString("id"));   ★ 
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				dto.setJoindate(rs.getString("joindate"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}
  ```
  
  ### 2. 다중 데이터를 검색
  ```java
  public List<UserDto> getUserList() {
		
		String sql = " SELECT ID, NAME, AGE, JOINDATE "
				+ " FROM USERTEST ";
		
		Connection conn = DBConnection.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<UserDto> list = new ArrayList<UserDto>();
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {     ★  데이터가 있을 경우 true 이기 때문에 계속 돌아가면서 list에 값을 넣어줄 수 있다.
				String id = rs.getString("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String joindate = rs.getString("joindate");
				
				UserDto dto = new UserDto(id, name, age, joindate);
				
				list.add(dto); 
						
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
  ```
  
  ## Main
  ```java
  import java.util.List;

import db.DBConnection;
import dto.UserDto;
import jdbc.SelectTest;

public class Main {

	public static void main(String[] args) {
		
		
		DBConnection.initConnection();
		
		SelectTest st = new SelectTest();   // statement 를 사용한 select
		
		String id ="abc";
		UserDto dto = st.search(id);
		if( dto != null) {
			System.out.println(dto.toString());
		}
		else {
			System.out.println("등록되어 있지 않은 ID 입니다.");
		}
		
		id = "abc3";               // preparedStatement 를 사용한 select
		dto= st.select(id);
		if( dto != null) {
			System.out.println(dto.toString());
		}
		else {
			System.out.println("등록되어 있지 않은 ID 입니다.");
		}
		
		List<UserDto> list = st.getUserList();    // 다중 데이터를 select
		for (UserDto user : list) {
			System.out.println(user.toString());
		}
	}
}
```
