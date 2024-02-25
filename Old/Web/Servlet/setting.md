# Servlet

### setting
- 디비의 자료를 서블릿이 불러와 클라이언트로 뿌려줌

![image](https://user-images.githubusercontent.com/65350890/87891267-589e2e00-ca74-11ea-822f-0bbdf8b2c830.png)

## 통신 해보기
- 톰캣의 conf에 있는 web.xml의 <web-app>부분 가져오기
- <servlet>태그 와 <servlet-mapping> 생성하여 name class 생성

```xml
<?xml version="1.0" encoding="UTF-8"?>

<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
  version="4.0">
  
  <!-- servlet class 등록 -->
  	<servlet>
  		<servlet-name>helloServlet</servlet-name>	
  		<servlet-class>sample01.HelloServlet</servlet-class> <!-- 패키지명까지 같이 적어주어야한다 -->
    </servlet>
  	
  	<servlet-mapping>
  		<servlet-name>helloServlet</servlet-name> <!-- servlet  태그의 이름과 동일 -->
  		<url-pattern>/location</url-pattern>
  	</servlet-mapping>
  
  </web-app>
```  

- Java에서 httpservlet 상속받아 오버라이드하기
```java
package sample01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("HelloServlet doget");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("HelloServlet doget");
	}

}
```
- client 웹 페이지에서 통신 하기
```html
<h1>Servlet</h1>
 <form action="location" method="get">
 	<input type="submit" value="get 방식">
 </form>
 
 <form action="location" method="post">
 	<input type="submit" value="post 방식">
 </form>
```
submit하면 통신되어 콘솔에 java에서 출력하려했던 문장이 출력된다.

## 자바 servlet toget, topost
```java
package sample01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//	System.out.println("HelloServlet doget");
		
		/*
		 Servlet -> Java코드안에(html)코드가 있는것
		 JSP => html영역에 (Java코드가 있는것) 
		 
		 */
		String name = req.getParameter("name");
		System.out.println("name: "+name);
		
		resp.setContentType("text/html; charset=utf-8");    // html문서의 문법대로 적어주어야한다.(노가다)
		
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>제목</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h1>Hello Servlet</h1>");
		pw.println("<p>Hello Servlet</p>");
		pw.println("<h3>name:"+name+"</h3>");
		pw.println("<a href='sample'>sample link</a>");
		
		pw.println("</body>");
		
		pw.println("</html>");
		pw.close();
	}

	@Override //post는 파라미터가 주소창 링크에 표시되지 않아 보안쪽(로그인, 회원가입)때 사용
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("HelloServlet doget");
	}

}
```
- 그래서 보통은 java 환경에서 method를 하나 생성해 놓고 불러와서 쓴다.
```java
package sample01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		createHTML("GET방식", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		createHTML("POST방식", req, resp);
	}
  
  
  
  
	public void createHTML(String methodType,HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String name = req.getParameter("name");
		System.out.println("name: "+name);
		
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>제목</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h1>Hello Servlet</h1>");
		pw.println("<p>"+methodType+"</p>");
		pw.println("<h3>name:"+name+"</h3>");
		pw.println("<a href='sample'>sample link</a>");
		
		pw.println("</body>");
		
		pw.println("</html>");
		pw.close();
	}
}
```
## 서버의 연결 및 ERROR
```html
<body>

<a href="hello?name='성춘향'">Hello Servlet move</a>   <-- 앵커태그도 값을 넘겨 줄 수 있다. -->
<br>

<h1>상태 코드 확인</h1>
<form action="hello" method="get">
<table>
<tr>
	<td>상태코드</td>
	<td>
		<select name="code">
			<option value="200">SC_OK</option>
			<option value="404">SC_NOT_FOUND</option>
			<option value="500">SC_INTERNAL_SERVER_ERROR</option>
		</select>
	</td>
</tr>
</table>

<input type="submit" value="송신">

</form>
</body>
```
- select는 값이 무조건 있다.
### xml 파일
```xml
<servlet>
  	<servlet-name>HelloServlet</servlet-name>
  	<servlet-class>sample04.HelloServlet</servlet-class>
  </servlet>
  
  <servlet-mapping>
  	<servlet-name>HelloServlet</servlet-name>
  	<url-pattern>/hello</url-pattern>   <-- url도 임의설정 가능 -->
  </servlet-mapping>
```
```java
package sample04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	@Override // 앵커와 form은 메서드를 설정하지 않으면 get으로 넘겨준다
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//	System.out.println("HelloServlet doGet");
	//	String name = req.getParameter("name"); <a>도 값을 넘길 수 있음
	//	System.out.println("name:"+name);
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>제목</title>");
		pw.println("</head>");
		pw.println("<body>");
		
		String code= req.getParameter("code");
		if(code.equals("200")) {
			pw.println("<p>200:SC_OK</p>");
		}
		else {
			if(code.equals("404")) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, "못 찾겠다라는 에러");
			}
			else if(code.equals("500")) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "코드가 틀렸다는 에러");
			}
		}
		pw.println("</body>");
		
		pw.println("</html>");
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
```



