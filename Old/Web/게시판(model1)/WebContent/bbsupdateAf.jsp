<%@page import="dao.BbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int seq = Integer.parseInt(request.getParameter("seq"));
String nwtitle = request.getParameter("nwTitle");
String nwcont = request.getParameter("nwcont");

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
boolean isS = dao.updateContent(nwtitle, nwcont, seq);
if(isS){
%>
	<script type="text/javascript">
		alert("수정 되었습니다.");
		location.href="bbslist.jsp";
	</script>

<% 
}else{
%>	
	<script type="text/javascript">
		alert("수정 실패");
		location.href="bbsdetail.jsp?seq="+seq;
	</script>
<%
}
%>


</body>
</html>