# Servlet을 활용한 link 이동
1. setheader를 이용
-html
```html
<body>

<p>Redirect 설정</p>

<!-- 
	html 
	- <a href
	- <form action -> submit
	
	Java script
	- location.href
	
	Java
	- sendRedirect
 -->
 
 <form action="hello" method="post">
 <select name="url">
 	<option value="naver.com">Naver</option>
 	<option value="google.com">Google</option>
 	<option value="zum.com">Zum</option>
 </select>
 <input type="submit" value="송신">
 </form>
 <br><br>
</body>
```
- web.xml
```xml
  <servlet>
  	<servlet-name>ServletHello</servlet-name>
	<servlet-class>sample05.HelloServlet</servlet-class>  	
  </servlet>
  
  <servlet-mapping>
  	<servlet-name>ServletHello</servlet-name>
  	<url-pattern>/hello</url-pattern>
  </servlet-mapping>
```
- 자바 toPost 버전
```java
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	System.out.println("hello doPost");
		
	// link 를 설정한 경우 반드시 셋팅
	resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
	String url = req.getParameter("url");
	if(url.equals("naver.com")) {
		resp.setHeader("Location", "http://www.naver.com");	//(setHeader 함수)선택한 링크로 location하여 이동
	}
	else if(url.equals("google.com")) {
		resp.setHeader("Location", "http://www.google.com");
	}
	else {
		resp.setHeader("Location", "http://www.zum.com");
	}
}
```
2. sendRedirect 이용
- html
```html
<form action="world" method="get">
 	<select name="url">
 		<option value="naver.com">네이버</option>
 		<option value="daum.net">다음</option>
 	</select>
	<input type="submit" value="송신">
 </form>
```
- web.xml
```xml
  <servlet>
  	<servlet-name>WorldServlet</servlet-name>
  	<servlet-class>sample05.WorldServlet</servlet-class>
  </servlet>
	
  <servlet-mapping>
	<servlet-name>WorldServlet</servlet-name>
	<url-pattern>/world</url-pattern>
  </servlet-mapping>
```  
- 자바 servlet
```java
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getParameter("url");
		
		if(url.equals("naver.com")) {
			resp.sendRedirect("http://www.naver.com");	//sendRedirect를 이용하여 해당 주소로 이동
		}
		else {
			resp.sendRedirect("http://www.daum.net");
		}
	}
```
### setheader의 방식보다 단순 링크 이동의 경우 sendRedirect가 더 많이 이용된다.(꼭 웹페이지가 아닌 index.html로도 이동 가능)

# 여러개의 값 다른 web으로 넘겨주기 (Servlet -> Servlet)
- html(JQuery를 이용하여 submit()함수를 이용하여 값을 넘겨주었다)
```html
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>

<h1>앙케이트 조사 입니다</h1>

<form action="hello" id="frm" method="get">
<table>
	<tr>
		<td>이름</td>
		<td>
			<input type="text" name="name" size="20" value="홍길동">
		</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>
			<input type="text" name="age" value="25">
		</td>
	</tr>
	<tr>
		<td>좋아하는 과일</td>
		<td>
			<select name="fruit" multiple="multiple"> 
				<option value="apple" selected="selected">사과</option>
				<option value="pear">배</option>
				<option value="banana">바나나</option>
			</select>
		</td>
	</tr>
</table>
<button type="button" id="btn">송신</button>
</form>

<script type="text/javascript">
$(document).ready(function() {
	$("#btn").click(function() {
		$("#frm").submit();
	});
});
</script>

</body>
```
- Web.xml
```xml
  <servlet>
  	<servlet-name>hello</servlet-name>
  	<servlet-class>sample06.HelloServlet</servlet-class>
  </servlet>
  
  <servlet-mapping>
  	<servlet-name>hello</servlet-name>
  	<url-pattern>/hello</url-pattern>
  </servlet-mapping>
  
  
  <servlet>
  	<servlet-name>result</servlet-name>
  	<servlet-class>sample06.ResultServlet</servlet-class>
  </servlet>
  
  <servlet-mapping>
  	<servlet-name>result</servlet-name>
  	<url-pattern>/result</url-pattern>
  </servlet-mapping>
```  
- 첫번째 client (html)에서 받은 값 받는 Servlet
```java
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String sAge = req.getParameter("age");
		
		System.out.println("name:"+name);
		System.out.println("age:"+sAge);
		
		int age = 0;
		if(sAge == null || sAge.trim().equals("")) {
			resp.sendRedirect("index.html"); //잘못 입력시 다시 그 페이지로
		}
		else {
			age = Integer.parseInt(sAge);
		}
		String fruits[] = req.getParameterValues("fruit");
		for (int i = 0; i < fruits.length; i++) {
			System.out.println(fruits[i]);
		}

    //단순 링크 이동 resp.sendRedirect("result"); 이용가능
    // resp.sendRedirect("result?name="+name+"&age"+age+"&fruit="+fruits[0]); 의 형식으로 넘겨주고 받는 Servlet에서 파라미터로 받을 수 있지만 그냥 사용하면 에러난다.
    
    // 값 포함 시키기
		ObjectDto dto = new ObjectDto(name, age, fruits); // Dto를 만들고 인스턴스생성
		
		req.setAttribute("myfruit", dto); // 값을 세팅하여 넘겨주기 (나중에 get하기위해 이름부여, 넣을 값(object))

    /* RequestDispatcher dispatch = req.getRequestDispatcher("result");
		  dispatch.forward(req, resp);*/
		req.getRequestDispatcher("result").forward(req, resp);  //위의 코드와 동일한 내용
   }
```
- 여러개의 값을 받기 위한 DTO 생성
- 통신시 사용하는 dto는 무조건 시리얼번호 부여 (인터페이스 Serializalbe)
```java
package dto;

import java.io.Serializable;
import java.util.Arrays;

public class ObjectDto implements Serializable {

	private String name;
	private int age;
	private String fruit[];
	
	public ObjectDto() {
	}

	public ObjectDto(String name, int age, String[] fruit) {
		super();
		this.name = name;
		this.age = age;
		this.fruit = fruit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String[] getFruit() {
		return fruit;
	}

	public void setFruit(String[] fruit) {
		this.fruit = fruit;
	}

	@Override
	public String toString() {
		return "ObjectDto [name=" + name + ", age=" + age + ", fruit=" + Arrays.toString(fruit) + "]";
	}
}
```
- 여러개의 값을 받고 출력할 Servlet
```java
import dto.ObjectDto;

public class ResultServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ObjectDto dto = (ObjectDto)req.getAttribute("myfruit"); // 캐스트 변환 (getAttribute는 object로 리턴)
		
		System.out.println(dto.toString()); // 값이 제대로 넘어오는지 
		
		
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>제목</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h1>Hello result</h1>");
		pw.println("이름:<input type='text' value="+dto.getName()+">");
		pw.println("나이:<input type='text' value="+dto.getAge()+">");
		String fruits[] = dto.getFruit();
		for (int i = 0; i < dto.getFruit().length; i++) {
			pw.println("<p>"+fruits[i]+"</p>"); // 한번에 넘겨주기때문에 for문을 통해 받은값 전부 출력
		}
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
```  
