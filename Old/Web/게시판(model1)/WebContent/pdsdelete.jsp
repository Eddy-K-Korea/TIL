<%@page import="dao.PdsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
int seq = Integer.parseInt(request.getParameter("seq"));

PdsDao dao = PdsDao.getInstance();

boolean isS = dao.deletePds(seq);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(isS){
	%>
	<script type="text/javascript">
	alert('삭제 완료');
	location.href="pdslist.jsp";
	</script>
	<%
}else{
	%>
	<script type="text/javascript">
	alert('삭제 실패');
	location.href="pdslist.jsp";
	</script>
	<%
}
%>


</body>
</html>