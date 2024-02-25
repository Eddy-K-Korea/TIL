<%@page import="dto.MemberDto"%>
<%@page import="dao.BbsDao"%>
<%@page import="dto.BbsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int seq = Integer.parseInt(request.getParameter("seq"));
BbsDao dao = BbsDao.getInstance();
BbsDto bbs = dao.getBbsdto(seq);
dao.readcount(seq);
Object ologin = session.getAttribute("login");
MemberDto mem = (MemberDto)ologin;


//System.out.println(bbs.toString());
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 작성자
	제목
	작성일
	조회수
	정보
	내용	
-->
<h1>작성한 글</h1>
<table border="1px solid" >
<col width="100px"><col width="300px">
<tr>
	<th colspan="2"><%=bbs.getId() %>님이 작성하신 글</th><th>작성일</th><td><%=bbs.getWdate() %></td><th>조회수</th><td><%=bbs.getReadcount()%></td>
</tr>
<tr>
	<th>정보</th><td colspan="5"><%=bbs.getRef() %>-<%=bbs.getStep() %>-<%=bbs.getDepth() %></td>
</tr>
<tr>
	<th >제목</th><td align="center" colspan="5"><%=bbs.getTitle() %></td>
</tr>
<tr>
	<th colspan="6">내용</th>
</tr>
<tr>
	<td colspan="6"><%=bbs.getContent() %></td>
</tr>
</table>

<%
if(bbs.getId().equals(mem.getId())){
	%>
	<button type="button" onclick="updateBbs(<%=bbs.getSeq() %>)">수정</button>
	<button type="button" onclick="deleteBbs(<%=bbs.getSeq() %>)">삭제</button>
	<% 
}
%>

<%-- <button type="button" onclick="answerBbs(<%=bbs.getSeq() %>)">댓글</button> --%>

<form action="answer.jsp" method="get">
	<input type="hidden" name="seq" value="<%=bbs.getSeq()%>">
	<input type="submit" value="댓글">
</form>

<script type="text/javascript">
function updateBbs(seq) {
	location.href = "bbsupdate.jsp?seq="+seq;
	
}

function deleteBbs(seq) {
	location.href = "bbsdelete.jsp?seq="+seq;
	
}

</script>





</body>
</html>