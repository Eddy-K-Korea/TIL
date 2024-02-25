<%@page import="bit.com.spring.dto.CalendarDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<CalendarDto> list = (List<CalendarDto>)request.getAttribute("callist");
%>
    
 <table class="list_table" style="width: 70%">
<col width="400"><col width="100"><col width="200">

	
	<tr>
	<th colspan="3" style="font-size: 16px; background: white; color: black">${year }년${month }월</th>
	</tr>

<tr>
	<th>제목</th><th>일정 시간</th><th>작성일</th>	
</tr>
<%
if(list.size()!=0){
	for(int i = 0; i < list.size(); i++){
		CalendarDto dto = list.get(i);
		%>
		<tr>
			<td><a href="caldetail.do?seq=<%=dto.getSeq() %>"><%=dto.getTitle() %></a></td>
			<td><%=dto.getRdate().substring(0, 4) %>년<%=dto.getRdate().substring(4, 6) %>월<%=dto.getRdate().substring(6, 8) %>일<%=dto.getRdate().substring(8, 10) %>시 <%=dto.getRdate().substring(10, 12) %>분</td>
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