<%@page import="dto.CustUserDto"%>
<%@page import="dao.CustUserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String id = request.getParameter("id");
String name = request.getParameter("name");
String address = request.getParameter("address");

System.out.println(id+" "+name+" "+address);
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

boolean isS = dao.addCustUser(new CustUserDto(id,name,address));

if(isS){
%>
	<script type="text/javascript">
	alert('성공적으로 추가 되었습니다.');
	location.href = "custuserlist.jsp";
	</script>
<%
}else{
%>
	<script type="text/javascript">
	alert('추가되지 않았습니다.');
	location.href = "custuseradd.jsp";
	</script>
<%
}
%>
</body>
</html>
