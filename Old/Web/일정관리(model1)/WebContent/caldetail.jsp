<%@page import="calendarEx.CalendarDao"%>
<%@page import="calendarEx.CalendarDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
int seq = Integer.parseInt(request.getParameter("seq"));

CalendarDao dao = CalendarDao.getInstance();
CalendarDto dto = dao.getCalDetail(seq);

String year = dto.getRdate().substring(0, 4);
String month = dto.getRdate().substring(4, 6);
String day = dto.getRdate().substring(6, 8);
String hour = dto.getRdate().substring(8, 10);
String min = dto.getRdate().substring(10, 12);

System.out.println(year+" "+month+" "+day+" "+hour+" "+min);

//System.out.println(dto.toString());

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>일정 자세히 보기</h1>
<table border="1">
<col width="100"><col width="300">
<tr>
	<th>작성자</th><th><%=dto.getId() %>
</tr>
<tr>
	<th>일정 시간</th><th><%=year %>년 <%=month %>월 <%=day %>일 <%=hour %>시 <%=min %>분</th>
</tr>
<tr>
	<th>일정 제목</th><td align="center"><%=dto.getTitle() %></td>
</tr>	
<tr>
	<th colspan="2">내용</th>
</tr>
<tr>
	<td colspan="2"><%=dto.getContent() %></td>
</tr>
<tr>
	<td colspan="2" align="center"><button type="button" id="update_cal">수정</button> <button type="button" id="delete_cal">삭제</button></td>

</tr>
</table>
<button type="button" id="golist">목록</button>

<script type="text/javascript">
$(function() {
	$("#update_cal").click(function() {
		location.href="calupdate.jsp?seq="+<%=seq%>;
	});
	$("#delete_cal").click(function() {
		location.href="caldelete.jsp?seq="+<%=seq%>;
	});
	$("#golist").click(function() {
		location.href="calendar.jsp";
	});
});


</script>
</body>
</html>