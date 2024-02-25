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
</head>
<body>
<%
BbsDao dao = BbsDao.getInstance();

boolean isS = dao.bbsDelete(seq);
if(isS){
	%>
	<script type="text/javascript">
		alert("글이 삭제 되었습니다.");
		location.href = "bbslist.jsp";
	</script>
<%
}else{
%>
	<script type="text/javascript">
		alert("삭제 실패");
		location.href = "bbslist.jsp";
	</script>
<%
}
%>

</body>
</html>