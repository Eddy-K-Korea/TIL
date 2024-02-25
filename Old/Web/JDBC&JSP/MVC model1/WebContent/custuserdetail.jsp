<%@page import="dto.CustUserDto"%>
<%@page import="java.util.List"%>
<%@page import="dao.CustUserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
String name;
String addr;
%>    
<% CustUserDao dao = CustUserDao.getInstance();
	/* List<CustUserDto> list = dao.getCustUserList();  내가 한 방법*/
	
	
	String id = request.getParameter("id");
	
	
	/* for(int i = 0; i < list.size(); i++){
		String userid = list.get(i).getId();
		if(userid.equals(id)){
			name = list.get(i).getName();
			addr = list.get(i).getAddress();
		}
	} 이렇게 일일이 찾지 않고 dto로 DB에서 다시 찾은다음 */
	
	CustUserDto dto = dao.getCustuser(id);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>고객 정보</h1>
<table>
<col width="100px"><col width="200px">
<tr>
	<td height="2" bgcolor="green" colspan="2"></td>
</tr>
<tr>
	<th bgcolor="yellow">ID</th><td ><%-- <%=id %> --%><%=dto.getId() %></td>
</tr>
<tr>
	<td height="2" bgcolor="green" colspan="2"></td>
</tr>
<tr>
	<th bgcolor="yellow">이름</th><td ><%-- <%=name %> --%><%=dto.getName() %></td>
</tr>
<tr>
	<td height="2" bgcolor="green" colspan="2"></td>
</tr>
<tr>
	<th bgcolor="yellow">주소</th><td><%-- <%=addr %> --%><%=dto.getAddress() %></td>
</tr>
<tr>
	<td height="2" bgcolor="green" colspan="2"></td>
</tr>
<tr>
	<td align="center"><button type="button" id="updBtn">수정</button>
	<%-- <form action="custuserupdate.jsp">
	<input type="hidden" name="id" value="<%=dto.getId()%>">  히든은 감쳐진 상태로 태그를 설정하고 아이디 , 값을 줄 수 있다. 
	<input type="submit" value="수정"></form> --%>
	</td>
	<td align="center"><button type="button" id="delBtn">삭제</button></td>
	
</tr>
</table>



<script type="text/javascript">

$(document).ready(function() {
	$("#updBtn").click(function() {
		location.href="custuserupdate.jsp?id="+"<%=id%>"+"&name="+"<%=name%>"+"&address="+"<%=addr%>";
		// id 값을 가져오는 방법 : 1. 태그에 id값을 부여하여 text를 가져오는 방법
		//                        2. form 을 사용하여 보내기(히든사용) 
		//                        3. 위의 방식대로 자바의 값을 가지고 올때 앞뒤로 " "를 붙여주고 넘김
	});
	$("#delBtn").click(function() {
		location.href = "custuserdelete.jsp?id="+"<%=id%>";
	});
});

</script>

</body>
</html>
