<%@page import="java.util.Calendar"%>
<%@page import="calendarEx.CalendarDto"%>
<%@page import="calendarEx.CalendarDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int seq = Integer.parseInt(request.getParameter("seq"));
CalendarDao dao = CalendarDao.getInstance();
CalendarDto dto = dao.getCalDetail(seq);

String year = dto.getRdate().substring(0, 4);
String month = dto.getRdate().substring(4, 6);
int m = Integer.parseInt(month);
String day = dto.getRdate().substring(6, 8);
int d = Integer.parseInt(day);
String hour = dto.getRdate().substring(8, 10);
int h = Integer.parseInt(hour);
String min = dto.getRdate().substring(10, 12);
int mi = Integer.parseInt(min);
Calendar cal = Calendar.getInstance();

int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH) + 1; // 0 ~ 11
int tday = cal.get(Calendar.DATE);
int thour = cal.get(Calendar.HOUR_OF_DAY);
int tmin = cal.get(Calendar.MINUTE);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>일정 자세히 보기</h1>
<div align="center">
<form action="calupdateAf.jsp" method="post">

<table border="1">
<col width="200"><col width="500">
<tr>
	<th>ID</th>
	<td>
		<%=dto.getId() %>
		<input type="hidden" name="seq" value="<%=seq %>">
	</td>

</tr>
<tr>
	<th>제목</th>
	<td>
		<input type="text" size="60" name="title" value="<%=dto.getTitle()%>"> 
	</td>
</tr>
<tr>
	<th>일정</th>
	<td>
		<select name="year">
		<%
			for(int i = tyear - 5; i < tyear +5; i++){
				%>
				<option <%=year.equals(i+"")?"selected='selected'":"" %> value="<%=i%>">
				<%=i %>
				</option>
				<%
			}
		
		%>
		</select>년
		
		<select name="month">
		<%
			for(int i = 1; i <= 12; i++){
				%>
				<option <%=m==i?"selected='selected'":"" %> value="<%=i%>">
				<%=i %>
				</option>
				<%
			}
		
		%>
		</select>월
		
		<select name="day">
		<%
			for(int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
				%>
				<option <%=d==i?"selected='selected'":"" %> value="<%=i%>">
				<%=i %>
				</option>
				<%
			}
		
		%>
		</select>일
		<select name="hour">
		<%
			for(int i = 0; i < 24; i++){
				%>
				<option <%=h==i?"selected='selected'":"" %> value="<%=i%>">
				<%=i %>
				</option>
				<%
			}
		
		%>
		</select>시
		<select name="min">
		<%
			for(int i = 0; i < 60; i++){
				%>
				<option <%=mi==i?"selected='selected'":"" %> value="<%=i%>">
				<%=i %>
				</option>
				<%
			}
		
		%>
		</select>분
	</td>
</tr>
<tr>
	<th>내용</th>
	<td>
		<textarea rows="20" cols="60" name="content" ><%=dto.getContent() %></textarea>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="submit" value="일정 수정">
	</td>
</tr>

</table>
</form>
</div>
</body>
</html>