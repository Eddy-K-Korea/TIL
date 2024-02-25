<%@page import="dao.CustUserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	
	String id = request.getParameter("id");
	String address = request.getParameter("address");

	System.out.println(address+" "+id);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
CustUserDao dao = CustUserDao.getInstance();
boolean isUp = dao.updateCustUser(id, address);

if(isUp){
	%>
	<script type="text/javascript">
	alert("저장 되었습니다.");
	location.href = "custuserlist.jsp";
	</script>
<%	
}else{
%>
	<script type="text/javascript">
	alert("저장 실패");
	location.href = "custuserupdate.jsp";
	</script>

<% 
}
%>
</body>
</html>