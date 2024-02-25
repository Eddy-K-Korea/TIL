<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>고객 추가</h1>

<form action="custuseraddAf.jsp">

<table>
<col width="100px"><col width="300px">
<tr>
	<td height="2" bgcolor="#ff0000" colspan="3"></td>
</tr>

<tr bgcolor="#f6f6f6">
	<th>아이디</th>
	<td>
		<input type="text" name="id" size="20">
	</td>
</tr>
<tr>
	<td height="2" bgcolor="#ff0000" colspan="3"></td>
</tr>
<tr bgcolor="#f6f6f6">
	<th>이름</th>
	<td>
		<input type="text" name="name" size="20">
	</td>
</tr>
<tr>
	<td height="2" bgcolor="#ff0000" colspan="3"></td>
</tr>
<tr bgcolor="#f6f6f6">
	<th>주소</th>
	<td>
		<input type="text" name="address" size="20">
	</td>
</tr>
<tr>
	<td height="2" bgcolor="#ff0000" colspan="3"></td>
</tr>

<tr bgcolor="#f6f6f6">
	<td align="center" colspan="2">
		<input type="submit" value="고객추가">
		<input type="reset" value="취소">
	</td>
</tr>

</table>

</form>

</body>
</html>







