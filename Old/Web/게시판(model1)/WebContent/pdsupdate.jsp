<%@page import="dto.PdsDto"%>
<%@page import="dao.PdsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

int seq = Integer.parseInt(request.getParameter("seq"));

PdsDao dao = PdsDao.getInstance();
PdsDto pds = dao.getPdsDetail(seq);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="pdsupdateAf.jsp">
<table border="1">
<col width="200"><col width="500">

<tr>
	<th>아이디</th><td><%=pds.getId() %><input type="hidden" name="seq" value="<%=pds.getSeq()%>"></td>
</tr>

<tr>
	<th>제목</th>
	<td><input type="text" name="title" value="<%=pds.getTitle() %>"></td>
</tr>

<tr>
	<th>파일 업로드</th>
	<td>
		<input type="text" name="oldfile" size="50" value="<%=pds.getFilename() %>">
	</td>
</tr>
<tr>
	<th>변경 파일업로드</th>
	<td>
		<input type="file" name="fileload" style="width:400px">
	</td>
</tr>

<tr>
	<th>내용</th>
	<td><textarea rows="20" cols="50" name="content"><%=pds.getContent() %></textarea></td>
</tr>

<tr align="center">
	<td colspan="2">
		<input type="submit" value="수정완료">
	</td>
</tr>
</table>

</form>


</body>
</html>