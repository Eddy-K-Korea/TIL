<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	paging 연산
	
	[1] ~ [10] [11] ~ [20]
	
	<< < [1] [2] 3 [4] [5] > >>
			41 ~ 50
--%>

<%
	int totalRecordCount;	// 전체 글 수			23 -> 3페이지
	int pageNumber;			// 현재 페이지			0 ~ 9 -> [1] ~ [10]
	int pageCountPerScreen;	// 스크린당 페이지 수		[1] ~ [10], [1] ~ [5]
	int recordCountPerPage;	// 페이지당 글 수		[1] -> 10, 20
	
	String st1 = request.getParameter("totalRecordCount");
	if(st1 == null) totalRecordCount = 0;
	else			totalRecordCount = Integer.parseInt(st1);
	
	String st2 = request.getParameter("pageNumber");	
	if(st2 == null) pageNumber = 0;
	else			pageNumber = Integer.parseInt(st2);
	
	String st3 = request.getParameter("pageCountPerScreen");
	if(st3 == null) pageCountPerScreen = 0;
	else			pageCountPerScreen = Integer.parseInt(st3);
	
	String st4 = request.getParameter("recordCountPerPage");
	if(st4 == null) recordCountPerPage = 0;
	else			recordCountPerPage = Integer.parseInt(st4);

	// 총 페이지 수		23 -> 3 페이지
	int totalPageCount = totalRecordCount / recordCountPerPage;
	//		2				23					10
	if( (totalRecordCount % recordCountPerPage) != 0 ){
		totalPageCount++;		// 3
	}
	
	// 시작 페이지		[1]	[11] [21]
	int screenStartPageIndex = ((pageNumber + 1) / pageCountPerScreen) * pageCountPerScreen;
	//			0					3					10					10
		
	// 끝 페이지
	int screenEndPageIndex = (((pageNumber + 1) / pageCountPerScreen) * pageCountPerScreen) + pageCountPerScreen;
	//			10						3					10					10						10
	
	// 끝 페이지는 다시 계산	[1] ~ [10]	-> [1] ~ [3]
	if(screenEndPageIndex > totalPageCount){
		screenEndPageIndex = totalPageCount;
	}
	
	// 0 ~ 9 -> 1 ~ 10
	if((pageNumber + 1) % pageCountPerScreen == 0){
		screenStartPageIndex = (((pageNumber + 1) / pageCountPerScreen) * pageCountPerScreen) - pageCountPerScreen;
		screenEndPageIndex = pageNumber + 1;		// 0 -> 1	9 -> 10	
	}	
	
%>

<!-- paging 출력 -->
<div style="float: left; width: 96%; text-align: center;">
	<!-- << -->
	<a href="#none" title="처음페이지" onclick="goPage(0)">
		<img alt="" src="./image/arrow_first.gif" style="width: 10px; height: 10px">
	</a>
	
	<!-- < -->		<!-- 총 글 수가 100개가 넘어야 보인다 -->
	<%				// [11] ~ [20] -> [1] ~ [10]
	if(screenStartPageIndex > 1){	// screenStartPageIndex 1=(1 ~ 10) 2=(11 ~ 20)
		%>
		<a href="#none" title="이전페이지" onclick="goPage('<%=screenStartPageIndex-1 %>')">
			<img alt="" src="./image/arrow_back.gif" style="width: 10px;height: 10px">
		</a>		
		<%
	}	
	%>
	
	<!-- [1] 2 [3] -->
	<%
	for(int i = screenStartPageIndex; i < screenEndPageIndex; i++){
		if(i == pageNumber){	// 현재 페이지
			%>
			<span style="font-size: 10pt; color: #000000; font-weight: bold;">
				<%=i+1 %>
			</span>
			<%
		}
		else{					// 그외의 페이지
			%>
			<a href="#none" title="<%=i+1 %>페이지" onclick="goPage(<%=i %>)"
				style="font-size: 9.5pt; color: #000000; font-weight: normal;">
				[<%=i+1 %>]
			</a>	
			<%
		}		
	}	
	%>	
	
	<!-- > -->
	<%
	if(screenEndPageIndex < totalPageCount){
		%>	
		<a href="#none" title="다음페이지" onclick="goPage(<%=screenEndPageIndex %>)">
			<img alt="" src="./image/arrow_next.gif" style="width: 10px; height: 10px">
		</a>		
		<%
	}	
	
	int end_page = 0;
	if(totalPageCount > 0){
		end_page = totalPageCount - 1;	
	}	
	%>
	
	<!-- >> -->
	<a href="#none" title="마지막페이지" onclick="goPage(<%=end_page %>)">
		<img alt="" src="./image/arrow_end.gif" style="width: 10px; height: 10px">	
	</a>
</div>



















