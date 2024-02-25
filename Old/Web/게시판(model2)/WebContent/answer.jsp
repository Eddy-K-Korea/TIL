<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
boolean s = (boolean)request.getAttribute("isS");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(s){
	%>
	<script type="text/javascript">
		alert("답글 추가 성공");
		location.href="conBbs?work=movblist";
	</script>
	<%
}else{
	%>	
	<script type="text/javascript">
		alert("추가 실패");
		location.href="conBbs?work=movblist"
	</script>
<%
}
%>

</body>
</html>