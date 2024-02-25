<%@page import="dto.CustUserDto"%>
<%@page import="java.util.List"%>
<%@page import="dao.CustUserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<CustUserDto> list = (List<CustUserDto>)request.getAttribute("custlist"); // 서블릿에서 보낸 리스트를 받음 

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>custUserList</title>
</head>
<body>

<h1>고객 목록 </h1>

<form action="muldel" method="post">
<table style="width: 700">
<col width="100"><col width="300"><col width="300">
<tr>
	<td height="2" bgcolor="#0000ff" colspan="3"></td>
</tr>
<tr>
	<th bgcolor="#ffff00">
		<input type="checkbox" name="alldel" onclick="deletechecks(this.checked)">
	</th>
	<th>ID</th>
	<th>Name</th>
<tr>
	<td height="2" bgcolor="#0000ff" colspan="3"></td>
</tr>
<%
if(list.size() == 0){	//데이터가 없다
	%>
	<tr bgcolor="#f6f6f6">
		<td colspan="3" align="center">고객 리스트가 존재 하지 않습니다.</td>
	</tr>
	<tr>
	<td height="2" bgcolor="#0000ff" colspan="3"></td>
	</tr>
<%	
}else{	// 데이터가 있다
	for(int i = 0; i < list.size(); i++){
		CustUserDto cust = list.get(i);
		%>
		<tr bgcolor="#f6f6f6">
			<td align="center" bgcolor="yellow">
				<input type="checkbox" name="delck" value="<%=cust.getId()%>">
			</td>
			<td>
				<%=cust.getId()%>
			</td>
			<td>
				<a href="detailuser?id=<%=cust.getId()%>"><%=cust.getName() %></a>
			</td>
		</tr>
		<tr>
			<td height="2" bgcolor="#0000ff" colspan="3"></td>
		</tr>
		<%
	}
}
	
%>

<tr>
	<td align="center" height="1" bgcolor="#c0c0c0" colspan="3">
		<!-- 다중 삭제 -->
		<input type="submit"  value="고객정보 삭제">
	</td>
</tr>
<tr>
	<td height="2" bgcolor="#0000ff" colspan="3"></td>
</tr>

<tr bgcolor="#f6f6f6">
	<td colspan="3">
	<a href="adduser?work=addview">고객정보 추가</a>
	</td>
</tr>

</table>
</form>

<script type="text/javascript">
/* let count = 0;
function deletechecks() {
	let user = document.getElementsByName("delck");
	if(count == 0){
		for (let i = 0; i < user.length; i++) {
				user[i].checked = true;
				count = 1;
		}
	}
	else if(count == 1){
		for (let i = 0; i < user.length; i++) {
			user[i].checked = false;
			count = 0;
		}
	}
} */

function deletechecks(ch) {
	let arrCheck = document.getElementsByName("delck");
	for(i=0; i < arrCheck.length; i++){
		arrCheck[i].checked = ch;
	}
}
</script>

</body>
</html>