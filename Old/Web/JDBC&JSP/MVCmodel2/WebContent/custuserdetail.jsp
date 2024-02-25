<%@page import="dto.CustUserDto"%>
<%@page import="java.util.List"%>
<%@page import="dao.CustUserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<% 
CustUserDto dto = (CustUserDto)request.getAttribute("custUser");
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
		location.href="updateUser?work=updateview&id="+"<%=dto.getId()%>";;
		// id 값을 가져오는 방법 : 1. 태그에 id값을 부여하여 text를 가져오는 방법 2. form 을 사용하여 보내기(히든사용) 3. 위의 방식대로 자바의 값을 가지고 올때 앞뒤로 " "를 붙여주고 넘김
	});
	$("#delBtn").click(function() {
		location.href = "updateUser?work=deleteuser&id="+"<%=dto.getId()%>";
	});
});

</script>

</body>
</html>