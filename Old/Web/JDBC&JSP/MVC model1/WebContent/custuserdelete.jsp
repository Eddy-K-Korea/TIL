<%@page import="dao.CustUserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<%
	String id = request.getParameter("id");
	
	System.out.println(id);
	
	CustUserDao dao = CustUserDao.getInstance();
	boolean isDel = dao.deleteCustUser(id);
	
	if(isDel){
%> 
		<script type="text/javascript">
			alert('삭제되었습니다.');
			location.href = "custuserlist.jsp";
		</script>
<%
}else{
%>
		<script type="text/javascript">
			alert('삭제 실패');
			location.href = "custuserdetail.jsp";
		</script>

<%}%>
</body>
</html>
