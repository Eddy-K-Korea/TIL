<%@page import="dto.MemberDto"%>
<%@page import="dao.BbsDao"%>
<%@page import="dto.BbsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<%
BbsDao dao = BbsDao.getInstance();

BbsDto parentBbs = dao.getBbsdto(seq);

Object ologin = session.getAttribute("login");
MemberDto mem = (MemberDto)ologin;
%>

<!-- 
기본글
	작성자
	제목
	작성일
	조회수
	정보
	내용

댓글
	아이디 login id <- session id
	제목
	내용 

 -->
 <h1>기본글</h1>
<table border="1px solid" >
<col width="100px"><col width="300px">
<tr>
	<th colspan="2"><%=parentBbs.getId() %>님이 작성하신 글</th><th>작성일</th><td><%=parentBbs.getWdate() %></td><th>조회수</th><td><%=parentBbs.getReadcount()%></td>
</tr>
<tr>
	<th>정보</th><td colspan="5"><%=parentBbs.getRef() %>-<%=parentBbs.getStep() %>-<%=parentBbs.getDepth() %></td>
</tr>
<tr>
	<th >제목</th><td align="center" colspan="5"><%=parentBbs.getTitle() %></td>
</tr>
<tr>
	<th colspan="6">내용</th>
</tr>
<tr>
	<td colspan="6"><%=parentBbs.getContent() %></td>
</tr>
</table>
<br><br><br><br>

<h2>답글</h2>
<form id="frm" method="post">
<input type="hidden" name="seq" value="<%=parentBbs.getSeq()%>">
<table>
<tr>
<th>아이디:</th><td> <input type="text" value="<%=mem.getId() %>" name="id" readonly="readonly"></td>
</tr>
<tr>
<th>제목</th><td><input type="text" name="answerTitle"></td>
</tr>
<tr>
<th>내용</th><td><textarea rows="4" cols="40" name="answerContent"></textarea></td>
</tr>

</table>
 </form>
 <button type="button" id="sub_btn">댓글 달기</button>
 
 <script type="text/javascript">
 $(function () {
	$("#sub_btn").click(function() {
		$("#frm").attr("action","answerAf.jsp").submit();
	});
});
 </script>

</body>
</html>

<!-- ref : 그룹 번호 (seq 번호이다)(부모글 번호)
	step: 일렬(순번)으로 정렬된다, 가장 처음 올린부분이 맨밑으로 내려간다.
	depth: 답글의 답글일 경우 하나씩 안쪽으로 들어간다. (답변의 답변은 depth가 하나씩 증가)

 -->


