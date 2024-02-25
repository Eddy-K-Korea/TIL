<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- ${callfile.seq}<br>
	${callfile.originfilename}<br> --%>
	<c:forEach items="${callfile}" var="safe">
		<img src="image/${safe}" width="300px, height:300px "><br>
	</c:forEach>

</body>
</html>