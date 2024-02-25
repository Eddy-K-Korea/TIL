<%@page import="dto.MemberDto"%>
<%@page import="dto.BbsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
BbsDto bbs = (BbsDto)request.getAttribute("BbsDto");
MemberDto mem = (MemberDto)session.getAttribute("login");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
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
if(bbs.getId().equals(mem.getId())){ // 작성자(세션 아이디와 글을 작성한 아이디가 동일해야만 버튼 보이게함)
	%>
	<button type="button" id="add_btn" value="<%=bbs.getSeq() %>">수정</button>
	<button type="button" id="del_btn" value="<%=bbs.getSeq() %>">삭제</button>
	<% 
}
%>


<button type="button" id="answer_btn">댓글</button>
<br><br><br><br>
<form id="frm" action="conBbs">
<input type="hidden" name="work" value="answer">
<input type="hidden" name="seq" value="<%=bbs.getSeq()%>">  
<table border="1px solid" id="tb1">
<tr>
	<th>작성자</th><td><input type="text" name="id" value="<%=bbs.getId() %>" readonly="readonly"></td>
</tr>
<tr>
	<th>제목</th><td><input type="text" name="title" size="70"></td>
</tr>
<tr>
	<th colspan="2" >내용</th>
</tr>
<tr>
	<td colspan="2"><textarea  rows="13" cols="80" name="content"></textarea>
</tr>
<tr>
	<td><input type="submit" value="답글 추가">
</tr>
</table>
</form>
<script type="text/javascript">
$(function() {
	$("#tb1").hide();
	$("#add_btn").click(function() {
		$.ajax({
			url:"conBbs",
			type:"get",
			datatype:"json",
			data:"work=updatecon",
			success: function() {
				
			},
			error: function() {
				alert('error');
			}
		});
	});
	
	$("#del_btn").click(function() {
		$.ajax({
			url:"conBbs",
			type:"get",
			datatype:"json",
			data:"work=deletecon&seq="+<%=bbs.getSeq()%>,
			success: function(data) {
				var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
				var t = data.replace(regExp, "");
				if(t=="success"){
					alert("글이 삭제 되었습니다.");
					location.href="conBbs?work=blist";
				}else{
					alert('삭제 실패');
				}
			},
			error: function() {
				alert('error');
			}	
		});
	});
	$("#answer_btn").click(function() {
		$("#tb1").show();
	});
	
	
});


</script>
</body>
</html>