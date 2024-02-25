# Session(세션)

### 세션이란?
- 일정 시간동안 같은 브라우저로 부터 들어오는 일련의 요구를 하나의 상태로 보고 그 상태를 유지하는 기술 즉, 웹 브라우저를 통해 웹 서버에 접속한 이후로 브라우저를 종료할 때 까지 유지되는 상태
클라이언트가 Request를 보내면, 해당 서버의 엔진이 클라이언트에게 유일한 ID를 부여하는 데 이것이 세션ID다.

- 세션 사용 사례 (로그인 정보 유지)
### 1. html
```html
<a href="hello">hello Servlet</a><br>
<a href="loginId">loginId Servlet</a>
```
### 2. helloServlet
```java
package sample09;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/hello") // 경로 설정
public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>제목</title>");
		pw.println("</head>");
		pw.println("<body>");

		HttpSession session = req.getSession(false);  
		/*
		 	getSession(false)
		 	session Object가 존재하면, 현재 HttpSession을 반환한다.
		 	존재하지 않으면 , null을 반환
		 	
		 	getSession(true)
		 	session Object가 존재하면, 현재 HttpSession을 반환한다.
		 	존재하지 않으면 , 새로 생성한다.
		 */
		if(session == null) {
			session = req.getSession(true);
			session.setMaxInactiveInterval(30);//365*24*60*60 1년 세션 저장 기간
			session.setAttribute("visited", "1"); // 첫세션 임의 생성
			pw.println("<p>첫번째 방문입니다.</p>");
			
		}else {
			String visited = (String)session.getAttribute("visited");
			int count = Integer.parseInt(visited);  // 이미 생성된 visited 세션이 있으면 카운트하여 방문횟수 표기
			
			count++;
			
			pw.println("<p>방문회수는"+count+"번째 방문입니다.</p>");
			session.setAttribute("visited", count+"");
		}
		
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
```
### 3. LoginServlet 
```java
package sample09;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginId")
public class LoginIdServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>제목</title>");
		pw.println("</head>");
		pw.println("<body>");
		
		pw.println("<h3>login id session</h3>");
		
		HttpSession session = null;	//항상 먼저 오브젝트부터 잡아놓아야한다.
		if(session == null) {
			pw.println("<p>session을 등록합니다</p>");
			session = req.getSession(true); 
			
			session.setAttribute("id", "abc123");
			session.setAttribute("name", "홍길동");
			session.setAttribute("visited", "1");
		}
		
		/*  //위에서 생성한 세션을 아래의 방식으로 출력 가능
		 * String id = (String)session.getAttribute("id"); 
		 * String name =(String)session.getAttribute("name");
		 * pw.println("<p>id:"+id+"</p>");
		 * pw.println("<p>name:"+name+"</p>");
		 */
		//여러개의 세션 출력시 아래 방법 사용
		Enumeration<String> enum_session = session.getAttributeNames();
		
		while(enum_session.hasMoreElements()) {
			String key = enum_session.nextElement();
			String value = (String)session.getAttribute(key);
			pw.println("<p>"+key+":"+value+"</p>");
		}
		
		pw.println("<a href=delobj>name 삭제</a>");
		pw.println("<a href=sessionDel>session 삭제</a>");
		
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
```
---

## 세션 삭제
### 1. key 삭제(obj삭제)
```java
package sample09;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/delobj")
public class DeleteObject extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		session.removeAttribute("name");	// 세션은 살아있고 안에있는 속성만 지운것
		Enumeration<String> enum_session = session.getAttributeNames();
		
		while(enum_session.hasMoreElements()) {
			String key = enum_session.nextElement();
			String value = (String)session.getAttribute(key);
			System.out.println("<p>"+key+":"+value+"</p>");
		}
	}
}
```
### 2. Session 삭제
```java
package sample09;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionDel")
public class SessionDelete extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		session.invalidate(); // delete(삭제), release(해방)
		if(req.getSession(false)==null) {
			System.out.println("session이 비어 있습니다.");
		}
	}
}
```

