<%@page import="dao.PdsDao"%>
<%@page import="dto.PdsDto"%>
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
<table border="1">
<col width="200"><col width="500">

<tr>
	<th>아이디</th><td><%=pds.getId() %></td>
</tr>

<tr>
	<th>제목</th>
	<td><%=pds.getTitle() %></td>
</tr>

<tr>
	<th>파일 업로드</th>
	<td>
		<%=pds.getFilename()%>
	</td>
</tr>

<tr>
	<th>내용</th>
	<td><textarea rows="20" cols="50" name="content" readonly="readonly"><%=pds.getContent() %></textarea></td>
</tr>

<tr align="center">
	<td colspan="2">
		<button type="button" id="update_pds" onclick="update_btn()">수정</button>
		<button type="button" id="delete_pds" onclick="delete_btn()">삭제</button>
	</td>
</tr>
</table>

<script type="text/javascript">
function update_btn() {
//	alert("update");
	location.href = "pdsupdate.jsp?seq="+<%=pds.getSeq()%>;
}

function delete_btn() {
//	alert('delete');
	location.href = "pdsdelete.jsp?seq="+<%=pds.getSeq()%>;
}


</script>






</body>
</html>