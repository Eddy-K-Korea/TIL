<%@page import="dto.BbsDto"%>
<%@page import="dao.BbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

int seq = Integer.parseInt(request.getParameter("seq"));	//목적: ref(그룹번호)설정
String id = request.getParameter("id");
String title = request.getParameter("answerTitle");
String content = request.getParameter("answerContent");

/* System.out.println("seq:"+seq);
System.out.println("id:"+id);
System.out.println("title:"+title);
System.out.println("content:"+content); */
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
boolean isS = dao.answer(seq, new BbsDto(id, title,content));

if(isS){
%>
	<script type="text/javascript">
	alert("답글 입력 성공");
	location.href="bbslist.jsp";
	</script>
<%
}else{
%>
	<script type="text/javascript">
	alert("답글 입력 실패");
	location.href="bbslist.jsp";
	</script>
<%
}
%>

</body>
</html>