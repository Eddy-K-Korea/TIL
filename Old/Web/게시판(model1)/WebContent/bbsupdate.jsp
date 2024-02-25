<%@page import="dto.BbsDto"%>
<%@page import="dao.BbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
int seq = Integer.parseInt(request.getParameter("seq"));
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<%
BbsDao dao = BbsDao.getInstance();
BbsDto dto = dao.getBbsdto(seq);
%>
<h1>작성한 글</h1>
<table border="1px solid" >
<col width="100px"><col width="300px">
<tr>
	<th colspan="2"><%=dto.getId() %>님이 작성하신 글</th><th>작성일</th><td><%=dto.getWdate() %></td><th>조회수</th><td><%=dto.getReadcount()%></td>
</tr>
<tr>
	<th>정보</th><td colspan="5"><%=dto.getRef() %>-<%=dto.getStep() %>-<%=dto.getDepth() %></td>
</tr>
<tr>
	<th >제목</th><td align="center" colspan="5"><input type="text" name="nwTitle" id='nwTitle' value="<%=dto.getTitle() %>" size="83"></td>
</tr>
<tr>
	<th colspan="6">내용</th>
</tr>
<tr>
	<td colspan="6"><textarea id='nwcont' name="nwcont" cols="100px" rows="10"><%=dto.getContent() %></textarea></td>
</tr>
</table>

<button type="button" id="complete">완료</button>
<button type="button" id="cancel">취소</button>

<script type="text/javascript">
$(function() {
	$("#complete").click(function() {
		location.href = "bbsupdateAf.jsp?nwTitle="+$("#nwTitle").val()+"&nwcont="+$("#nwcont").val()+"&seq="+<%=seq%>;
	});
	$("#cancel").click(function() {
		location.href = "bbsdetail.jsp?seq="+<%=seq%>;
	});
});

</script>
</body>
</html>