# JSP(Java Server Page)

![image](https://user-images.githubusercontent.com/65350890/88129805-f67c2f00-cc13-11ea-9ce1-f37fd5edb408.png)

### JSP 사용
- 내장(암시)객체: new를 통해서 생성하지 않고 바로 사용 할 수 있는 class
1. out			-> Scriptlet(java)에서 Web으로 출력해 주는 객체<br>
2. request		-> 매개변수의 값을 취득하는 경우 사용하는 객체, 객체를 저장시에도 사용<br>
3. response	-> 이동을 실시하는 객체 (sendRedirect)<br>
4. session 	-> 객체를 저장시에도 사용<br>
5. pageContext -> forward

- html 코드와 같이 사용
```JSP
<%
String str = "Hello JSP";
%>
<h2><%=str %></h2>
```
= 값을 넣을땐 <%= %>을 이용하면 값만 넣을 수 있다.
```JSP
<%
for(int i = 0; i < 10; i++){
%>
<p class="demo">p tag <%=i %></p>
<%
}
%>
```
= 9개의 p태그가 생성된다.

## scriptlet의 종류
#### 1. 선언부(함수)
```JSP
<%! // 선언의 영역: 변수(global)를 선언, 함수, class (!를 붙인다)
public void func(){
	System.out.println("func() 호출");
}
	int gl_num = 0;
%>
```
#### 구현부
```JSP
<%
// 소스부(구현부)
	func();
	int num = 0;
	
	gl_num++;
	num++;
	
	out.println("gl_num = "+ gl_num);
	out.println("<br>");
	out.println("num = "+ num);
%>
```
웹을 새로고침하게 되면 num은 <b>지역변수</b>로 계속 1에 머물지만, *매개변수*로 선언된 gl_num은 계속 +1 된다.

#### 2. 선언부(클래스)
```JSP

<%! // 사실 Java 클래스 및 함수는 java resources 폴더에 생성하고 import하여 사용한다.
class MyClass{
	private int num;
	private String name;
	
	public MyClass(){
		num = 2;
		name = "World";
	}
	public String print(){
		return num + " " + name;
	}
}
%>
```
- 위처럼 한 document에 선언할 수 있지만 보통 java 폴더에 생성하고 import한다
```jsp
<%@page import="sample01.YouClass"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
```
```JSP
<body>

<%
MyClass mcls = new MyClass();
out.println(mcls.print());

out.println("<br>");
YouClass ycls = new YouClass(12,"홍길동");
out.println(ycls.toString());
%>

</body>
```
MyClass는 한 document에서 선언, YouClass는 외부에서 설정하여 Import하여 객체를 생성하여 출력하였다.
- 결과는 클래스 생성시 지정하였던 변수의 toString형태로 출력

# 내장 함수 
### request
```JSP
<body>

<%-- request(요청) --%>

<%
// = getParameter( name문자열 )

// setAttribute( 객체의 별명, Object )
// = getAttribute( 객체의 별명 )

//  [배열] = getParameterValues

	// HttpServletRequest 를 jsp에서 request만 사용
	String name = request.getParameter("name");
	System.out.println("name:"+name);
	
	String age = request.getParameter("age"); // 파라미터로 받게끔 설정하고 Web URL(주소창)에 index2?age=20 기재
	System.out.println("age:"+age); // 콘솔에 url에 기재하였던 age 출력
	
	String hobby[] = request.getParameterValues("hobby");
	
	if(hobby != null && hobby.length > 0){
		for(int i = 0; i < hobby.length; i++){
			System.out.println("hobby:"+hobby[i]);
		}
	}
%>
</body>
```
위의 구현부에서 설정한 request을 사용하여 설정해 놓았다. web url창에 파라미터이름과 값을 넣어주면, 콘솔창에 그 값을 확인 할 수 있다.
- 값을 넘겨 받는다는 의미

# JSP 값 넘기기
### 1. sendRedirect 만 사용하여 넘기기
```JSP
String name = "홍길동";
name = URLEncoder.encode(name);	//sendRedirect 할 시 한글은 인코딩을 해주어야 함
response.sendRedirect("NewFile.jsp?name="+name); 
```
```JSP
<%
String name = request.getParameter("name");
System.out.println("name:"+name);

%>
<input type="text" value="<%=name%>">
```
- 텍스트 창에 name값이 넘어온것을 확인 할 수 있다.
- 단, 여러개의 값을 넘길때 번거롭고 객체를 넘길 수 없다.

### 2. setAttribute , forward 사용
- 단순 파라미터
```JSP
String name = "일지매";
request.setAttribute("name", name); //짐싸
pageContext.forward("NewFile.jsp"); //잘가
```
```JSP
<%
String name = (String)request.getAttribute("name");
System.out.println("name:"+name);
%>

<input type="text" value="<%=name%>">
```
- 객체
```JSP
YouClass cls = new YouClass(1, "성춘향");
request.setAttribute("cls", cls);
pageContext.forward("NewFile1.jsp");
```
```JSP
<%@page import="sample01.YouClass"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% // 먼저 객체의 값을 받는다.
Object obj = request.getAttribute("cls");
if(obj == null){
	//조건을달아준다.
}
YouClass cls = (YouClass)obj;
%>
...
<body>

번호:<input type="text" value="<%=cls.getNum()%>">
<br>
이름:<input type="text" value="<%=cls.getName()%>">

</body>
```
- 객체를 넘길 수 있고, forward 함수를 사용하기 위해서는 * __pageContext__ *를 불러와야한다.

### 3. Session 을 이용한 값 넘기기
```JSP
<%
YouClass cls = new YouClass(2,"정수동");
session.setAttribute("ycls", cls);			// request.getSession().setAttribute("ycls",cls); 와 동일
response.sendRedirect("NewFile2.jsp");
%>
```
```JSP
<%@page import="sample01.YouClass"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
YouClass ycls = (YouClass)session.getAttribute("ycls");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<p><%=ycls.getNum() %></p>
<p><%=ycls.getName() %></p>

</body>
</html>
```
- 세션에 객체의 값을 저장하고 불러오는 형식. 
- 세션의 저장된 값은 꽤 긴 시간 남아있어 필요할때 불러올 수 있음
- 사용 예: 로그인 이후 다른 페이지에서 다시 DB에서 로그인 정보를 가져오는것이 아니라 세션에서 로그인 정보를 가져온다.

