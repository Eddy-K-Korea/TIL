<%@page import="bit.com.spring.util.PollUtil"%>
<%@page import="bit.com.spring.dto.MemberDto"%>
<%@page import="bit.com.spring.dto.PollDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
List<PollDto> plists = (List<PollDto>)request.getAttribute("plists");

MemberDto mem = (MemberDto)session.getAttribute("login");
%>    
<!-- 관리자 -->
<%
if(mem.getAuth() == 1){
%>
	<table class="list_table" style="width: 95%">
	<col width="50"><col width="50"><col width="300"><col width="100">
	<col width="100"><col width="50"><col width="60"><col width="100">	
	
	<tr>
		<th>번호</th><th>아이디</th><th>투표 제목</th><th>시작일</th>
		<th>종료일</th><th>보기수</th><th>투표 횟수</th><th>등록일</th>
	</tr>
	<%
		for(int i = 0; i< plists.size(); i++){
		PollDto poll = plists.get(i);
		%>
			<tr bgcolor="#aabbcc">
				<td><%=i+1 %></td>			
				<td><%=poll.getId() %></td>
				<td><%=poll.getQuestion() %></td>
				<td><%=poll.getSdate() %></td>
				<td><%=poll.getEdate() %></td>
				<td><%=poll.getItemcount() %></td>
				<td><%=poll.getPolltotal() %></td>
				<td><%=poll.getRegdate() %></td>
			</tr>
		<%
		}
	%>
	</table>
<%
}
%>





<!-- 일반유저 -->
<%
if(mem.getAuth() == 3){
%>
	<table class="list_table" style="width: 95%">
	<col width="50"><col width="50"><col width="300"><col width="100"><col width="100">
	<col width="100"><col width="50"><col width="60"><col width="100">	
	
	<tr>
		<th>번호</th><th>아이디</th><th>투표 제목</th><th>결과</th><th>시작일</th>		<!-- 진행중 / 종료 를 결과에 표시-->
		<th>종료일</th><th>보기수</th><th>투표 횟수</th><th>등록일</th>
	</tr>
	<%
	for(int i = 0; i < plists.size(); i++){
		PollDto poll = plists.get(i);
		%>
		<tr bgcolor="#aabbcc">
			<td><%=i+1 %></td>
			<td><%=poll.getId() %></td>
			
			<%//투표 제목
			boolean isS = poll.isVote();	//dto의 vote를 가져온다
			
				//투표 햇음		|| 		투표 기간이 끝났음		
			if(isS == true || PollUtil.isEnd(poll.getEdate())==true){	//투표를 못하게 막는 조건
				%>
				<td>[마감]<%=poll.getQuestion() %></td>
				<%
			}
			else{	//투표를 안하고, 투표기간이 남아있을 경우
				%>
				<td>
					<a href="polldetail.do?pollid=<%=poll.getPollid()%>">
						<%=poll.getQuestion() %>
					</a>
				</td>
				<%
				
			}
			%>
			<td><%//투표 결과 항목
			if(isS == true || PollUtil.isEnd(poll.getEdate())==true){	//결과 확인 가능
				%>
				<a href="pollresult.do?pollid=<%=poll.getPollid()%>">결과</a>
				<%
			}
			else{	//결과 확인을 할 수 없음
				%>
				<img alt="" src="image/pen.gif">
				<%
			}
			%></td>
			<td><%=poll.getSdate() %></td>
			<td><%=poll.getEdate() %></td>
			<td><%=poll.getItemcount() %></td>
			<td><%=poll.getPolltotal() %></td>
			<td><%=poll.getRegdate() %></td>
		</tr>
		<%
	}
	%>
	</table>
<%
}
%>

<%
if(mem.getAuth() ==1){
	%>
	<a href="pollmake.do">투표 만들기</a>
	<%
	
}
%>