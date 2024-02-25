# Session

### JSP 에서 세션은 내장함수로 생성이 가능하다.

```JSP
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int c;
	if(session.getAttribute("count") != null){  // 세션이 있을경우
		c = (Integer)session.getAttribute("count"); //세션의 count 값을 캐스팅변환하여 대입
	}else{  // 그게 아니면 첫방문으로 0으로 설정
		c= 0;
	}
	
	c++;  한번씩 늘려준다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>방문 횟수</h1>
<p>Count:<%=c %>회</p>
<%
session.setAttribute("count", new Integer(c));  //세션의 값과 수를 설정(값은 객체로 받을 수 있음), 처음이면 생성하게 된다
%>

</body>
</html>
```
