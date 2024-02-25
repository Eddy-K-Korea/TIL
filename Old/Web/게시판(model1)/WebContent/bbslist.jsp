<%@page import="dto.BbsDto"%>
<%@page import="java.util.List"%>
<%@page import="dao.BbsDao"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
//댓글의 depth와 이미지를 추가하는 함수   		depth =1   이면 한칸 띄어쓰기하고 이미지 
public String arrow(int depth){
	String rs = "<img src='./image/arrow.png' width='20px' height='20px'/>";
	String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
	
	String ts = "";
	for(int i = 0; i<depth; i++){
		ts +=nbsp;
	}
	return depth==0?"":ts + rs;
}

%>    
    
    
<%
Object ologin = session.getAttribute("login");
MemberDto mem = null;
if(ologin == null){	//세션이 날라갔을때 (시간이 많이 지나서)
	%>
	<script type="text/javascript">
	alert("로그인 해 주십시오");
	location.href = "login.jsp";	
	</script>
<%	
}
mem = (MemberDto)ologin;
%>

<%
//검색 방법 1
String searchWord = request.getParameter("searchWord");
String choice = request.getParameter("choice");

if(choice == null || choice.equals("")){
	choice = "sel";
}
//검색어를 지정하지 않고 Choice가 넘어 왔을때
if(choice.equals("sel")){
	searchWord = "";
}
if(searchWord == null){
	searchWord = "";
	choice = "sel";
}
%>


<%
BbsDao dao = BbsDao.getInstance();

String spageNumber = request.getParameter("pageNumber");

int pageNumber = 0; // 현재 페이지(첫번재 페이지)
if(spageNumber != null && !spageNumber.equals("")){
	pageNumber = Integer.parseInt(spageNumber);
}

System.out.println("pageNumber:"+pageNumber);

//List<BbsDto> list = dao.getBbsList();
/* List<BbsDto> list = dao.getBbsList(choice, searchWord); */
List<BbsDto> list = dao.getBbspagingList(choice, searchWord, pageNumber);

int len = dao.getAllBbs(choice, searchWord);

System.out.println("총 글의 갯수:"+len);

int bbsPage = len / 7; //예: 총 8개 ->2page
if(len% 7>0){
	bbsPage = bbsPage +1; //현재 2가 됨
}



%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	let searchWord = "<%=searchWord%>";
	if(searchWord=="") return;
	
	let obj = document.getElementById("choice");
	obj.value = "<%=choice%>";
	obj.setAttribute("selected", "selected");
	
});

</script>
</head>
<body>

<h4 align="right" style="background-color: #f0f0f0">환영합니다 <%=mem.getId() %>님</h4>

<h1>게시판</h1>

<a href="./calendar/calendar.jsp">일정관리</a>
<a href="./pdslist.jsp">자료실</a><br><br>

<div align="center">
<table border="1">
<col width="70"><col width="600"><col width="150">
<tr>
	<th>번호</th><th>제목</th><th>작성자</th>
</tr>

<%
if(list == null || list.size() == 0){
	%>
	<tr>
		<td colspan="3">작성된 글이 없습니다.</td>
	</tr>
<%
}else{
	for(int i = 0; i < list.size(); i++){
		BbsDto bbs = list.get(i);
%>
			<tr>
				<th><%=i+1 %></th>
				<td>
				<%
				if(bbs.getDel()==0){
					 %>
					<%=arrow(bbs.getDepth()) %>	<!-- 여백 + 이미지 -->
					<a href="bbsdetail.jsp?seq=<%=bbs.getSeq() %>">
						<%=bbs.getTitle() %>
					</a>
				
				<%
				}else{
				%>
			
				
					<%=arrow(bbs.getDepth()) %>	<!-- 여백 + 이미지 -->
					이 글은 삭제 되었습니다.
				<%
				}
				%>	
			
				<td align="center"><%=bbs.getId() %></td>
			</tr>
			<%
	}
}
%>
</table>
<%

	for(int i = 0; i < bbsPage; i++){
		if(pageNumber == i){	// 1 [2] [3] 이렇게 페이지  , 현재 페이지는 링크
			%>
			<span style="font-size: 15pt; color: blue; font-weight: bold; text-decoration: none" >
				<%=i+1 %>
			</span>&nbsp;
			<%
		}else{	// 그외 페이지
			%>
			<a href ="#none" title="<%=i+1 %>페이지" onclick="goPage(<%=i %>)" style="font-size: 15pt; color: black; font-weight: bold; text-decoration: none">
				[<%=i +1 %>]
			</a>&nbsp;
			<%
		}
	}
	
%>


</div>

<br><br>
<a href="bbswrite.jsp">글쓰기</a><br><br>




<!-- 검색 방법 1 -->
<select id="choice">
	<option value="sel">선택</option>
	<option value="id">작성자</option>
	<option value="title">제목</option>
	<option value="content">내용</option>
</select>
<input type="text" id="search" value="<%=searchWord%>">
<button onclick="searchBbs()">검색</button> 

<script type="text/javascript">
function searchBbs() {
	var choice = document.getElementById("choice").value;
	var word = document.getElementById("search").value;
//	alert(choice);
//	alert(word);


	location.href = "bbslist.jsp?searchWord=" + word + "&choice=" + choice;	//새로고침
	
}


function goPage(pageNum) {
	var choice = document.getElementById("choice").value;
	var word = document.getElementById("search").value;
	
	location.href = "bbslist.jsp?pageNumber="+pageNum+"&choice="+choice+"&searchWord="+word;

	
}

</script>


</body>
</html>








