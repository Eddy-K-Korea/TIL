# Cookie

### Web 저장 공간
![image](https://user-images.githubusercontent.com/65350890/88009836-6cb65e00-cb4e-11ea-89a5-65a99eb81bb6.png)
- 쿠키와 세션 모두 Object로 받아오는것은 set, getAttribute를 사용하여 forward를 이용한다.

1. html
```html
<a href="hello">hello Servlet</a>
```
2. HelloServlet
```java
package sample08;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello") // 경로 설정
public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Cookie cookie = new Cookie("id", "abc123");   //key ,value 값을 설정하여 쿠키 생성 (예시를 위해 지정생성)
		resp.addCookie(cookie);
		
		cookie = new Cookie("pwd", "aaa111");     //key ,value 값을 설정하여 쿠키 생성 (예시를 위해 지정생성)
		resp.addCookie(cookie);
		
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>제목</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<a href=dispCookie>Cookie를 표시</a>");
		
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
```
3. dispCookie를 통해 cookie 확인
```java
package sample08;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dispCookie")
public class displayCookie extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>제목</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h3>Cookie Display</h3>");
		
		Cookie cookies[] = req.getCookies();  // 전달 받은 쿠키를 배열에 넣기
		if(cookies != null) { //만약 쿠키가 있다면
			for (int i = 0; i < cookies.length; i++) {  // 배열을 확인하면서 name, value를 변수에 받아준다.
				String cookieName = cookies[i].getName();
				String value = cookies[i].getValue();
				
				pw.println("<p>"+cookieName+":"+value+"</p>");
			}
		} 
		pw.println("<a href=visitedCookie>방문 횟수를 표시</a>");  // 쿠키를 통해 방문횟수 확인
		pw.println("</body>");
		
		pw.println("</html>");
		pw.close();
	
	}
}
```
4. visitedCookie에서 방문횟수 값 확인
```java
package sample08;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/visitedCookie") // 경로 설정
public class VisitedServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>제목</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<p>방문횟수</p>");
		
		Cookie cookies[] = req.getCookies();  //쿠키 배열에 넣어주기
		Cookie visitedCookie = null;  // 방문횟수 변수선언
		
		if(cookies != null) { // 쿠키가 있을때
			//검색
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("visited")) {  //visited라는 이름의 쿠키를 찾음
					visitedCookie = cookies[i];   // 있다면 visitedCookie에 넣어줌
					break;
				}
			}
			//visited 찾았을때
			if(visitedCookie != null) { 
				int count = Integer.parseInt(visitedCookie.getValue()) + 1; //count를 통해 늘려준다.
				pw.println("<p>"+count+"번째 방문</p>");
				
				//cookie 값을 갱신
				visitedCookie.setValue(count+"");
				visitedCookie.setMaxAge(60);	//365*24*60*60 -> 1년  (쿠키 유지 시간 설정)
				resp.addCookie(visitedCookie);
				
			}
			else {//visited 못 찾았을때
					// 쿠키를 생성
				pw.println("<p>첫번째 방문</p>");
				Cookie newCookie = new Cookie("visited", "1");  //이름을 visited로 하기 때문에 위의 검색때 .equals("visited")로 한다
				resp.addCookie(newCookie);  //만들고 쿠키에 넣어준다.
				
			}
		}else {
    // 쿠키가 없을때
		}
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
```
- 해당 페이지에 갔을때 그 페이지에는 쿠키값이 있는 client가 들어오게 되고 접속한 페이지에서 visited라는 쿠키를 만들어 카운트하여 몇번 방문하였는지 확인,
