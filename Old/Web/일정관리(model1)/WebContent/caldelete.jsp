<%@page import="calendarEx.CalendarDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int seq = Integer.parseInt(request.getParameter("seq"));

CalendarDao dao = CalendarDao.getInstance();

boolean isS = dao.deleteCal(seq);
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
		alert("일정이 정상적으로 삭제 되었습니다.");
		location.href="calendar.jsp";
	</script>	
	<% 
}else{
	%>
	<script type="text/javascript">
		alert("일정 삭제 실패");
		location.href="calendar.jsp";
	</script>
	<%
}
%>

</body>
</html>