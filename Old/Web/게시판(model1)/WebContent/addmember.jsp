<%@page import="dto.MemberDto"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
boolean addmem = false;
MemberDao dao = MemberDao.getInstance();
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
String name = request.getParameter("name");
String email = request.getParameter("email");

//System.out.println(id+" "+pwd+" "+name+" "+email);

MemberDto dto = new MemberDto(id,pwd,name,email,3);

addmem = dao.addMember(dto);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
<%if(addmem){
	%>
	alert("회원가입 되었습니다.");
	location.href ="login.jsp";
<% 	
}else{
	%>
	alert("회원가입 실패");
<% 
}

%>
</script>
</body>
</html>