# Tiles 타일즈

## 타일즈를 이용하여 게시판과 로그인 페이지 재구성

- 타일즈는 웹 페이지의 상단이나 하단 메뉴와 같이 반복적으로 사용되는 부분들에 대한 코드를 분리해서 예쁘게 한 곳에서 관리를 가능하게 해주는 프레임워크이다.
### jsp include와 차이점
- 비슷한 역할이지만, tiles가 여러모로 편리하고 좋다. jsp는 페이지 내에 동일한 레이아웃 정보가 들어가므로 전체적인 레이아웃을 변경하게 될 경우 모든 페이지를 수정해야하는 문제점이 있다. 예를들면 만약 50개의 페이지에 상단 메뉴가 include 되어있는데, 페이지명이 바뀌면 50개 파일을 전부 수정해주어야 하는 것이다. tiles는 이런 일이 있으면 설정파일만 변경해주면 된다.

### pom.xml 라이브러리 추가
```xml
<!-- tiles -->
	<dependency>
	    <groupId>org.apache.tiles</groupId>
	    <artifactId>tiles-api</artifactId>
	    <version>3.0.7</version>
	</dependency>
	
	<dependency>
	    <groupId>org.apache.tiles</groupId>
	    <artifactId>tiles-core</artifactId>
	    <version>3.0.7</version>
	</dependency>
	
	<dependency>
	    <groupId>org.apache.tiles</groupId>
	    <artifactId>tiles-jsp</artifactId>
	    <version>3.0.7</version>
	</dependency>
	
	<dependency>
	    <groupId>org.apache.tiles</groupId>
	    <artifactId>tiles-servlet</artifactId>
	    <version>3.0.7</version>
	</dependency>
	
	<dependency>
	    <groupId>org.apache.tiles</groupId>
	    <artifactId>tiles-template</artifactId>
	    <version>3.0.7</version>
	</dependency>
	
	<dependency>
  		<groupId>org.apache.tiles</groupId>
  		<artifactId>tiles-autotag-core-runtime</artifactId>
  		<version>1.1.0</version>
  	</dependency>

	<dependency>
  		<groupId>org.apache.tiles</groupId>
  		<artifactId>tiles-request-api</artifactId>
  		<version>1.0.6</version>
  	</dependency>
  	
  	<dependency>
  		<groupId>org.apache.tiles</groupId>
  		<artifactId>tiles-request-jsp</artifactId>
  		<version>1.0.6</version>
  	</dependency>
  	
  	<dependency>
  		<groupId>org.apache.tiles</groupId>
  		<artifactId>tiles-request-servlet</artifactId>
  		<version>1.0.6</version>
  	</dependency> 
```

### servlet-context 의 설정 변경
- 아마 이전 까지는 아래의 코드대로 /WEB-INF/views/*.jsp 의 설정으로 모든 jsp 파일을 읽어와 뷰를 출력하였다.
```xml
<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/views/"></property>	view 경로 명
		<property name="suffix" value=".jsp"></property>	확장자명
</bean>
```
- 레이아웃을 읽어주도록 설정을 해주어야한다.
```xml
 <bean id="tilesConfigurer" class="org.springframework.web.servlet.view.tiles3.TilesConfigurer">
  <property name="definitions">
    <list>
      <value>/WEB-INF/views/layouts.xml</value>
    </list>
  </property>
 </bean>

 <bean id="viewResolver" class="org.springframework.web.servlet.view.UrlBasedViewResolver">
  <property name="requestContextAttribute" value="requestContext"/>
  <property name="viewClass" value="org.springframework.web.servlet.view.tiles3.TilesView"/>
 </bean>
 ```
 ### views폴더에 레이아웃이 되는 틀을 생성 (layouts-tiles.jsp)
 ```html
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 이전에 있던 도큐먼트의 구문을 삭제한다-->
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 화면 배치용 jsp -->

<table border="1" width="100%" height="100%" bordercolro="gray"> 

<tr align="center">			<!-- header -->
	<td height="10%" colspan="3">
		<tiles:insertAttribute name="header"/>
	</td>
</tr>

<tr>						<!-- menu, main -->
	<td width="20%" align="left" valign="top">
		<tiles:insertAttribute name="menu"/>
	</td>
	<td>
		<tiles:insertAttribute name="content"/>
	</td>
	<td width="15%" align="center" valign="middle">
		<tiles:insertAttribute name="act"/>
	</td>
</tr>

<tr align="center">			<!-- footer -->
	<td height="10%" colspan="3">
		<tiles:insertAttribute name="footer"/>
	</td>
</tr>

</table>
</body>
</html>
```

### 조립을 하기 위해 설정을 해주는 xml 파일을 생성(layouts.xml)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE tiles-definitions PUBLIC
       "-//Apache Software Foundation//DTD Tiles Configuration 3.0//EN"
       "http://tiles.apache.org/dtds/tiles-config_3_0.dtd">


<tiles-definitions>
	<definition name="login.tiles" template="/WEB-INF/views/layouts-tiles.jsp">
		<put-attribute name="header" value="/WEB-INF/views/header.jsp"/>
		<put-attribute name="menu" value="/WEB-INF/views/login/login.jsp"/>
		<put-attribute name="content" value="/WEB-INF/views/login/content.jsp"/>
		<put-attribute name="act" value="/WEB-INF/views/act.jsp"/>
		<put-attribute name="footer" value="/WEB-INF/views/footer.jsp"/>
	
	</definition>

	<definition name="regi.tiles" extends="login.tiles">    <!-- 중복되는 부분은 extends (상속)을 통해 그대로 진행하고 새롭게 변경되는 부분만 속성값을 바꿔준다 -->
		<put-attribute name="content" value="/WEB-INF/views/login/regi.jsp"/>
	</definition>


	<!-- bbs -->
	<definition name="bbslist.tiles" extends="login.tiles">
		<put-attribute name="content" value="/WEB-INF/views/bbs/bbslist.jsp"/>
	</definition>

	<definition name="bbswirte.tiles" extends="login.tiles">
		<put-attribute name="content" value="/WEB-INF/views/bbs/bbswrite.jsp"/>
	</definition>

	<definition name="bbsdetail.tiles" extends="login.tiles">
		<put-attribute name="content" value="/WEB-INF/views/bbs/bbsdetail.jsp"/>
	</definition>
	<definition name="bbssearch.tiles" extends="login.tiles">
		<put-attribute name="content" value="/WEB-INF/views/bbs/bbslist.jsp"/>
	</definition>
	<definition name="bbsupdate.tiles" extends="login.tiles">
		<put-attribute name="content" value="/WEB-INF/views/bbs/bbsupdate.jsp"/>
	</definition>

</tiles-definitions>
```
### 기본 배치에 사용되는 고정된 jsp 파일 생성 (header, footer, 광고,메뉴 등) 
- 내용이 바뀌는 content는 각 폴더를 만들어 관리를 하게된다.
1. Header
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h2>header</h2>
```
2. Footer
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<h2>footer</h2>
```
3. act
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<img src="./image/ex.gif" width="250px" height="400px">
```

### Controller 에서 tiles된 view를 접근할때는 layouts.xml에서 definition에 name값으로 지정해준 값으로 이동시켜준다
- MemberController
```java
package bit.com.spring.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.spring.dto.MemberDto;
import bit.com.spring.service.MemberService;

@Controller
public class MemberController {

	private static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService service;
	
	@RequestMapping(value = "login.do", method = {RequestMethod.GET,RequestMethod.POST}) //get, post 들어오는 방식 중 하나 선택
	public String login() {  			//(외부로부터 들어오는 값(object, String), Model,HttpServletRequest)
		logger.info("login "+new Date());
	
		return "login.tiles"; // -> *.jsp 이 아니라  layouts.xml에 설정한 name값으로
	}
	
	@RequestMapping(value = "regi.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String regi() {
		return "regi.tiles";
	}
	@RequestMapping(value = "regiAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String regiAf(MemberDto dto) {
		logger.info("login "+new Date());
		
		boolean b = service.addMember(dto);
		if(b) {
			logger.info("회원가입 되었습니다");
			return "login.tiles";
		}
		logger.info("가입되지 않았습니다");
		return "regi.tiles";
	}
	
	@RequestMapping(value = "loginAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String loginAf(MemberDto dto, HttpServletRequest req) {
		
		logger.info("loginAf "+new Date());
		
		MemberDto login = service.login(dto);
		
		if(login !=null && !login.getId().equals("")) {
			logger.info("login success "+new Date());
			//session저장
			req.getSession().setAttribute("login", login);
			//req.getSession().setMaxInactiveInterval(60 * 60);
			
			//이동
			return "redirect:/bbslist.do"; 
		}else {
			logger.info("login fail "+new Date());
			return "redirect:/login.do";
		}
	}
	@RequestMapping(value = "logout.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpServletRequest req) {
		//세션 삭제
		req.getSession().invalidate();
		
		return "redirect:/login.do";
	}
}
```
### 폴더를 관리하는 모습
![캡처](https://user-images.githubusercontent.com/65350890/92393023-28ad0600-f15a-11ea-91b6-0d10aa9f9e66.PNG)

