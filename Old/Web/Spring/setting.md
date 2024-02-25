 # 스프링 셋팅
 
 - 스프링 실행후 Help -> 이클립스 마켓플레이스 에서 legacy 다운로드
 - spring legacy 프로젝트 mvc 프로젝트 생성
 
 ### 위처럼 하면 자동적으로 기본 셋팅을 스프링이 해준다
 
 ### 스프링의 진행 및 모형도
 ![캡처](https://user-images.githubusercontent.com/65350890/91698168-3a683980-ebad-11ea-887f-b0d3d7f69c5f.PNG)
 
 ## 스프링 처럼 setting 해보기
 
 - 스프링의 실행 경로를 찍어보기위한 log 넣기
 - pom.xml에 log4j와 slf4j simple의 코드를 넣어준다
 ```xml
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>

<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>1.7.25</version>
</dependency>
```
 
 
 1. 프로젝트 오른쪽 클릭하여 java EE Tools에서 web.xml을 생성해준다
 - WebContent 에 WEB-INF 에 web.xml이 생성된다.
 ```xml
 <?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" version="3.1">
  <display-name>sample02</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
  
   <servlet>
  	<servlet-name>dispatcherServlet</servlet-name>
  	<servlet-class>
  		org.springframework.web.servlet.DispatcherServlet   <!-- 스프링프레임워크 디스패쳐서블릿 잡아준다 -->
  	</servlet-class>
  	
  	<init-param>
  		<param-name>contextConfigLocation</param-name>
  		<param-value>
  			/WEB-INF/spring/dispatcherServlet.xml <!-- 우리가 사용할 view의 확장자와 경로를 셋팅해주는 xml 으로 가게끔 경로를 잡아준다 -->
  		</param-value>
  	</init-param>
  	
  	<load-on-startup>1</load-on-startup> <!-- 이것부터 읽어라 라는 표시  -->
  	
  </servlet>
  
   <servlet-mapping>
  	<servlet-name>dispatcherServlet</servlet-name>
  	<!-- <url-pattern>/</url-pattern>  이 방법에서 아래 방법으로 사용도 한다-->
  	<url-pattern>*.do</url-pattern>
  </servlet-mapping>
  
  <!-- filter : 중간처리 A-> B -->
  <!-- 한글 설정 -->
   <filter>
  	<filter-name>encodingFilter</filter-name>
  	<filter-class>
  		org.springframework.web.filter.CharacterEncodingFilter
  	</filter-class>
  	
  	<init-param>
  		<param-name>encoding</param-name>
  		<param-value>UTF-8</param-value>
  	</init-param>
  	
  	<init-param>
  		<param-name>forceEncoding</param-name>
  		<param-value>true</param-value>
  	</init-param>  	
  </filter>
  
  <filter-mapping>
  	<filter-name>encodingFilter</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping>
</web-app>
```
- 실행했을때 읽혀지는 순서(web.xml -> index.jsp -> controller -> 링크를 찾고 return을 통해 해당하는 jsp 파일을 servlet-context.xml(dispatcherServlet.xml)에서 찾는다. 그리고 views에서 실행)

2. 위의 xml이 읽혀지고 index.jsp로 가게된다
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
response.sendRedirect("home.do"); //해당 이름으로 컨트롤로 가게된다.
%>
</body>
</html>
```
3. 이제 home.do를 가지고 컨트롤러로 가게된다.
```java
package bit.com.a;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.a.dto.Human;

@Controller   //컨트롤러로 오게된다.
public class HelloController {
	
	private static Logger logger = LoggerFactory.getLogger(HelloController.class);
	
									//form method="post" 와 같은 의미
	@RequestMapping(value = "home.do", method = RequestMethod.GET) // 아까 index.jsp 에서 home.do라고 준것을 여기서 받는다
	public String homeMethod(Model model) {
		logger.info("homeMethod()" + new Date());

		String name = "홍길동";	// request.setAttribute("name", name);  
		model.addAttribute("name", name); // 짐싸 값을 넘겨준다
		
		
		return "home";	//home.jsp로 이동
	}
	
}
```
4. 이제 servlet-context.xml(dispatcherServlet.xml) 으로 넘어와 view의 경로와 확장자명을 확인한다
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context" 
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">

	<!-- spring MVC annotation(주석문, 지시문)을 사용하기 위한 설정 아래 NameSpaces에서 context설정 -->
	<context:annotation-config />
	
	<!-- viewResolver 설정(사용자의 view의 위치, 확장자명) -->
	<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/views/"></property>	<!-- view 경로 명 -->
		<property name="suffix" value=".jsp"></property>	<!-- 확장자명 -->
	</bean>
	
	
	<!-- 
		위의 내용을 자바로 표현하면 아래와 같다
		
	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	viewResolver.prefix = "/WEB-INF/views/";
	viewResolver.suffix = ".jsp";
	
	 -->
	
	<!-- java 공통 패키지 -->
	<context:component-scan base-package="bit.com.a" />
	
</beans>
```
- context설정과 java공통 패키지에서(컨트롤위치)를 설정하고 view와 확장자를 설정해준다.

5. 이제 WEB-INF/views 파일에서 jsp파일을 모두 읽어 컨트롤에서 return해준 jsp파일로 값을 가지고 가게된다.
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
</head>
<body>

<%
String name = (String)request.getAttribute("name"); //값을 전달 받고 출력
out.println("name: "+name);

%>
<h3>${name }</h3> <!-- EL태그를 활용하여 값 출력-->
<br><br>

<a href="world.do?age=24&name=일지매">World로 이동</a>  <!-- 웹에서 입력받은 값을 다시 넘겨주기위해 설정 / 컨트롤러로 가게된다-->

</body>
</html>
```
- 반대로 웹에서 입력된 값을 넘겨받는 방식은 반대이다.

6. 아까 컨트롤러에서 값을 받기위한 메소드를 생성한다
```java
@RequestMapping(value = "world.do", method = RequestMethod.GET)
//public String world(int age, String name) {
public String world(Human h) {

//	logger.info("age:" + age);
//	logger.info("name:" + name);
  logger.info(h.toString());
  return "home";
}
```  
7. Human이라는 dto를 생성해준다
```java
package bit.com.a.dto;

import java.io.Serializable;

public class Human implements Serializable {

	private String name;
	private int age;
	
	public Human() {
	}

	public Human(String name, int age) {
		super();
		this.name = name;
		this.age = age;
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

	@Override
	public String toString() {
		return "Human [name=" + name + ", age=" + age + "]";
	}
}
```
-컨테이너에서 자동으로 값을 Human 객체에 넣고 생성하여 toString을 쓸수있게된다.
