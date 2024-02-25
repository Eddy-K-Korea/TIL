<%@page import="calendarEx.CalendarDto"%>
<%@page import="java.util.List"%>
<%@page import="calendarEx.CalendarDao"%>
<%@page import="util.UtilEx"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");

System.out.println(year+" "+month+" "+day);

String rdate = year + UtilEx.two(month)+ UtilEx.two(day);

CalendarDao dao = CalendarDao.getInstance();
List<CalendarDto> list = dao.getDateList(rdate);


%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>일정 목록</h1>
<h3><%=year %>년<%=month %>월<%=day %>일 </h3>
<table border="1">
<col width="400"><col width="100"><col width="200">
<tr>
	<th>제목</th><th>일정 시간</th><th>작성일</th>	
</tr>
<%
if(list.size()!=0){
	for(int i = 0; i < list.size(); i++){
		CalendarDto dto = list.get(i);
		%>
		<tr>
			<td><a href="caldetail.jsp?seq=<%=dto.getSeq() %>"><%=dto.getTitle() %></a></td>
			<td><%=dto.getRdate().substring(8, 10) %>시 <%=dto.getRdate().substring(10, 12) %>분</td>
			<td><%=dto.getWdate() %></td>
		</tr>
		<%
		}
		%>
			<%
}else if(list.size()==0){
		%>
		<tr>
			<td colspan="3">일정이 없습니다.</td>
		</tr>
		<%
}
%>




</table>
<button type="button" id="addcal">일정 추가</button> <button type="button" id="golist">목록</button>
<script type="text/javascript">
$(function() {
	$("#addcal").click(function() {
		location.href="calwrite.jsp?year="+<%=year%>+"&month="+<%=month%>+"&day="+<%=day%>;
	});
	$("#golist").click(function(){
		
		location.href="calendar.jsp";
	});
});

</script>
</body>
</html>