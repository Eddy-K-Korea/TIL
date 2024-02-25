<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MemberDto mem = (MemberDto)session.getAttribute("login");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>



<div>
	<form id="frm">
		<input type="hidden" name="work" value="addcontent">
		<div>
		작성자: <input type="text" name="id" readonly="readonly" value="<%=mem.getId()%>">
		</div>
		<div>제목: <input type="text" name="title"></div>
		<div>
			<h4>내용:</h4>
			<textarea rows="13" cols="80" name="content"></textarea>
		</div>
	</form>
	<button type="button" id="list_btn">목록</button>
	<button type="button" id="add_btn">글 추가</button>
</div>

<script type="text/javascript">
$(document).ready(function() {
	$("#list_btn").click(function() {
		location.href = "conBbs?work=back";
	});
	$("#add_btn").click(function() {
	//	$("#frm").attr("action","conBbs").submit();
	if($('input').val()==""){
		alert("제목을 채워주세요");
	}else if($('textarea').val()==""){
		alert("내용을 채워주세요");
	}else{
		$.ajax({
			url:"conBbs",
			type:"get",
			datatype:"json",
			data:$("#frm").serialize(),
			success: function(data){
				var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
				var t = data.replace(regExp, "");
				if(t=="success"){
					alert("작성하신 글이 추가 되었습니다.");
					location.href="conBbs?work=blist";
				}else{
					alert('추가 실패');
				}
			},
			error: function(){
				alert("error");
			}
		});
	}			
	});
});
</script>
</body>
</html>