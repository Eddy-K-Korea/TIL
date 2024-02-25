<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
Object ologin = session.getAttribute("login");
MemberDto mem = (MemberDto)ologin;

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

<div>
<p>ID: <%=mem.getId() %></p>
</div>
<div>
	<form id="frm">
		<div>제목: <input type="text" name="title"></div>
		<div>
			<h4>내용:</h4>
			<textarea rows="13" cols="80" name="content"></textarea>
		</div>
	</form>
	<button type="button" id="list_btn">목록</button>
	<button type="button" id="add_btn">글 추가</button>
</div>

<script type="text/javascript">
$(document).ready(function() {
	$("#list_btn").click(function() {
		location.href = "bbslist.jsp";
	});
	$("#add_btn").click(function() {
		$("#frm").attr("action","bbswriteAf.jsp").submit();
	});
});


</script>

</body>
</html>