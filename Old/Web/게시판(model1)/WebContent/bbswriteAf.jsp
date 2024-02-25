<%@page import="dao.BbsDao"%>
<%@page import="dto.BbsDto"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
Object ologin = session.getAttribute("login");
MemberDto mem = (MemberDto)ologin;
String title = request.getParameter("title");
String content = request.getParameter("content");
BbsDto bdto = new BbsDto(mem.getId(),title,content);

BbsDao dao = BbsDao.getInstance();
boolean addwrite = dao.writeBbs(bdto);
//System.out.println("title: "+title+ " content: "+ content);
//System.out.println(bdto.toString());
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(!addwrite){
	%>
	<script type="text/javascript">
		alert("제목, 내용을 모두 작성해 주세요");
		location.href="bbswrite.jsp";
	</script>
<% 	
}else{
%>
	<script type="text/javascript">
		alert("작성하신 글이 등록 되었습니다.");
		location.href="bbslist.jsp";
	</script>
<%
}
%>
</body>
</html>