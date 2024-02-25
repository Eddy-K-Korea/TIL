<%@page import="bit.com.spring.dto.CalendarDto"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
Calendar cal = Calendar.getInstance();
int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH) + 1; // 0 ~ 11
int tday = cal.get(Calendar.DATE);
int thour = cal.get(Calendar.HOUR_OF_DAY);
int tmin = cal.get(Calendar.MINUTE);

String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");

%>

<form action="calwriteAf.do" method="post" id="frm">

<table class="list_table" style="width: 50%">
<col width="40"><col width="300">
<tr>
	<th>ID</th>
	<td>
		${login.id }
		<input type="hidden" name="id" value="${login.id }">
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

</table>
	<span class="button blue" style="margin-top:15px; margin-left: auto; margin-right: auto">
		<input type="submit" value="일정 추가" id="addcal_btn">
	</span>
</form>

<script>
$(document).ready(function(){
	$("#addcal_btn").click(function(){
		$("#frm").submit();
	});
});

</script>