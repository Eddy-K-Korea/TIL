<%@page import="dto.MemberDto"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

//System.out.println("id:"+id+" pwd:"+pwd);


%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
MemberDao dao = MemberDao.getInstance();
MemberDto mem = dao.login(id, pwd);

// 로그인 성공
if(mem != null && !mem.getId().equals("")){
	// 로그인 정보 저장
	session.setAttribute("login", mem);
	session.setMaxInactiveInterval(30 * 60 * 60); // 로그인 정보 세션에 저장해놓고 저장 시간까지 설정
%>
	<script type="text/javascript">
		alert("안녕하세요 <%=mem.getName()%> 님")
		location.href = "./bbslist.jsp";
	</script>
<%	
}else{
%>
	<script type="text/javascript">
		alert("id나 password를 확인하십시오")
		location.href = "./login.jsp";
	</script>
<%
}
%>

</body>
</html>