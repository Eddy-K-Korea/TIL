<%@page import="java.util.Calendar"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");

MemberDto mem = (MemberDto)session.getAttribute("login");

System.out.println("year:"+year);
System.out.println("month:"+month);
System.out.println("day:"+day);

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//범위를 설정하는 기준
Calendar cal = Calendar.getInstance();

int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH) + 1; // 0 ~ 11
int tday = cal.get(Calendar.DATE);
int thour = cal.get(Calendar.HOUR_OF_DAY);
int tmin = cal.get(Calendar.MINUTE);

%>

<h1>일정 추가</h1>
<div align="center">
<form action="calwriteAf.jsp" method="post">

<table border="1">
<col width="200"><col width="500">
<tr>
	<th>ID</th>
	<td>
		<%=mem.getId() %>
		<input type="hidden" name="id" value="<%=mem.getId() %>">
	</td>

</tr>
<tr>
	<th>제목</th>
	<td>
		<input type="text" size="60" name="title"> 
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
				<option <%=month.equals(i+"")?"selected='selected'":"" %> value="<%=i%>">
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
				<option <%=day.equals(i+"")?"selected='selected'":"" %> value="<%=i%>">
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
				<option <%=(thour+"").equals(i+"")?"selected='selected'":"" %> value="<%=i%>">
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
				<option <%=(tmin+"").equals(i+"")?"selected='selected'":"" %> value="<%=i%>">
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
		<textarea rows="20" cols="60" name="content"></textarea>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="submit" value="일정 추가">
	</td>
</tr>

</table>
</form>
</div>

</body>
</html>