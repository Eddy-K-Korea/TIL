# 기본 셋팅 Test

1.오라클 연동<br>
2. Dynamic  Web project -> WEB-INF에 lib에 ojdbc 가져다 놓기<br>
3. 코딩 셋팅<br>
4. (우선 DB의 테이블을 끌어오는 코딩)Test<br>
```html
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>HR Tables</h1>

<p>모든 테이블의 목록을 출력한다</p>

<%
// Java 영역  (DB 연동)
Class.forName("oracle.jdbc.driver.OracleDriver");

String url="jdbc:oracle:thin:@192.168.7.45:1521:xe";
String user = "hr";
String password = "hr";

Connection conn = DriverManager.getConnection(url, user, password);

String sql = "SELECT * FROM TAB";

PreparedStatement psmt = conn.prepareStatement(sql);
ResultSet rs = psmt.executeQuery();

ResultSetMetaData rsmd = rs.getMetaData(); // column 정보 취득할때 사용
int count = rsmd.getColumnCount(); 	// column의 숫자

%>
<table border="1">
<tr>
	<%
	for(int i = 1; i < count + 1; i++){
	%>
		<th><%=rsmd.getColumnName(i) %></th>		
	<%
	}
	%>
</tr>
<%
while(rs.next()){
%>
	<tr>
		<%
		for(int i = 1; i < count +1; i++){
			String cols = rs.getString(i);
		%>
			<td><%=cols %></td>
		<%
		}
		%>
	</tr>


<%
}
%>
</table>


</body>
</html>
```
![image](https://user-images.githubusercontent.com/65350890/88035559-4d82f500-cb7d-11ea-8ac3-6ee7c96f9b67.png)
- 테이블이 쭉 출력 되는걸 확인 할 수 있다.
