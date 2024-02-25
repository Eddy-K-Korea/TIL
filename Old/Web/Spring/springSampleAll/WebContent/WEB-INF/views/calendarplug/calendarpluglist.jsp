<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="bit.com.spring.dto.CalendarPlugDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link href="<%=request.getContextPath() %>/callib/main.css" rel="stylesheet" />
<script src="<%=request.getContextPath() %>/callib/main.js"></script>

<%
List<CalendarPlugDto> list = (List<CalendarPlugDto>)request.getAttribute("callist");
for(CalendarPlugDto c: list){
	System.out.println(c.toString());
}



//JSONObject, JSONArray

String events = "[";
for(CalendarPlugDto c: list){
	events += "{id:'"+c.getId() +"',title:'"+c.getTitle()+ "', start:'"+c.getStartdate()+"', end:'"+ c.getEnddate()+"', constranint:'"+c.getContent()+"'},"; 
}
events = events.substring(0,events.lastIndexOf(","));		//맨끝 , 제거

events += "]";

System.out.println(events);
request.setAttribute("events", events);
%>
<style>
.body {
  margin: 40px 10px;
  padding: 0;
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  font-size: 12px;
}

#calendar {
  max-width: 900px;
  margin: 0 auto;
} 
</style>

<script>
document.addEventListener("DOMContentLoaded",function(){

	let calendarEl = document.getElementById("calendar");
	
		
	let calendar = new FullCalendar.Calendar(calendarEl, {

		headerToolbar:{
			left:"prev,next today",
			center:"title",
			right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
		},
		initialDate: new Date(), // new Date() 도 가능함 (처음 실행시 기준이 되는 날짜)
		locale: 'ko',		//한글 설정
		navLinks: true,		//네비게이션 날짜를 가르키는 설정
		businessHours: true,
		events:<%=request.getAttribute("events")%>
	});
	calendar.render();
});	

</script>

<div class="body">
	<div id="calendar"></div>
</div>











