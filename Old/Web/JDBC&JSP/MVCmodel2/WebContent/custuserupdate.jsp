<%@page import="dao.CustUserDao"%>
<%@page import="dto.CustUserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
CustUserDto dto = (CustUserDto)request.getAttribute("userdetail");	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>고객 정보 수정</h1>
<table>
<col width="100px"><col width="200px">
<tr>
	<td height="2" bgcolor="green" colspan="2"></td>
</tr>
<tr>
	<th bgcolor="yellow">ID</th><td ><%=dto.getId() %></td>
</tr>
<tr>
	<td height="2" bgcolor="green" colspan="2"></td>
</tr>
<tr>
	<th bgcolor="yellow">이름</th><td ><%=dto.getName() %></td>
</tr>
<tr>
	<td height="2" bgcolor="green" colspan="2"></td>
</tr>
<tr>
	<th bgcolor="yellow">주소</th><td><input type="text" id="newAddr" value="<%=dto.getAddress() %>"></td>
</tr>
<tr>
	<td height="2" bgcolor="green" colspan="2"></td>
</tr>
<tr>
	<th colspan="2"><input type="submit" id="updSaveBtn" value="저장"></th>
</tr>
</table>

<script type="text/javascript">
$(document).ready(function() {
	$("#updSaveBtn").click(function() {
		location.href = "updateUser?work=saveuser&address="+$("#newAddr").val()+"&id="+"<%=dto.getId()%>";
	});
});
</script>

</body>
</html>