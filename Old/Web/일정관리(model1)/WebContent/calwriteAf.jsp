<%@page import="calendarEx.CalendarDto"%>
<%@page import="calendarEx.CalendarDao"%>
<%@page import="util.UtilEx"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
String id = request.getParameter("id");
String title = request.getParameter("title");
String content = request.getParameter("content");
String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");
String hour = request.getParameter("hour");
String min = request.getParameter("min");

System.out.println(id+" "+title+" "+ content);
System.out.println(year+" "+month+" "+ day+" "+hour+" "+min);

// 2020/07/31 14:52 ->202007311452 (RDATE)에 들어가는 형식

String rdate = year + UtilEx.two(month)+ UtilEx.two(day)+ UtilEx.two(hour)+UtilEx.two(min);

System.out.println(rdate);

CalendarDao dao = CalendarDao.getInstance();

boolean isS = dao.addCalendar(new CalendarDto(id,title,content,rdate));
if(isS){
	%>
	<script type="text/javascript">
		alert("일정이 추가 되었습니다.")
		location.href="calendar.jsp";
	</script>
	<%
}else{
	%>
	<script type="text/javascript">
	alert("일정 추가 실패")
	location.href="calendar.jsp";
	</script>
	<%
}
%>





</body>
</html>









