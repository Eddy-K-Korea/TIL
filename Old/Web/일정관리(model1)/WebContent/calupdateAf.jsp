<%@page import="calendarEx.CalendarDao"%>
<%@page import="util.UtilEx"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
int seq = Integer.parseInt(request.getParameter("seq"));
String title = request.getParameter("title");
String content = request.getParameter("content");

String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");
String hour = request.getParameter("hour");
String min = request.getParameter("min");

String rdate = year + UtilEx.two(month)+ UtilEx.two(day)+ UtilEx.two(hour)+UtilEx.two(min);

System.out.println(seq+" "+title+" "+ content);
System.out.println(year+" "+month+" "+ day+" "+hour+" "+min);

CalendarDao dao = CalendarDao.getInstance();
boolean isS = dao.updateCal(seq, title, content, rdate);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(isS){
	%>
	<script type="text/javascript">
		alert("일정 수정 되었습니다.");
		location.href="calendar.jsp";
	</script>
	<%
}else{
	%>
	<script type="text/javascript">
		alert("일정 수정 실패");
		location.href="calupdate.jsp?seq="+<%=seq%>;
	</script>
	<%
}
%>
</body>
</html>