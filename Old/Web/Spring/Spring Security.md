# Spring Security

### 인증의 구분을 두어 관리자와 일반 회원을 나누어 접근할 수 있는 페이지를 나눈다

### 1. spring security 라이브러리 다운
```xml
<!-- Spring Security -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-core</artifactId>
    <version>5.2.2.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-web</artifactId>
    <version>5.2.2.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-config</artifactId>
    <version>5.2.2.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-taglibs</artifactId>
    <version>5.2.2.RELEASE</version>
</dependency>
```
### 2. web.xml을 설정해준다
- 필터와 시큐리티 설정xml을 연결해준다
```xml
<servlet>
  	<servlet-name>dispatcherServlet</servlet-name>
  	<servlet-class>
  		org.springframework.web.servlet.DispatcherServlet
  	</servlet-class>
  	
  	<init-param>
  		<param-name>contextConfigLocation</param-name>
  		<param-value>
  			/WEB-INF/spring/servlet-context.xml
  			/WEB-INF/spring/intercepter-context.xml 
  		</param-value>
  	</init-param>
  	
  	<load-on-startup>1</load-on-startup>
  	
  </servlet>
  
   <servlet-mapping>
  	<servlet-name>dispatcherServlet</servlet-name>
  	<url-pattern>/</url-pattern>
  </servlet-mapping>
  
  <context-param>
  	<param-name>contextConfigLocation</param-name>
  	<param-value>
  		/WEB-INF/spring/context-security.xml  <!--시큐리티 설정 xml연결 -->
  		/WEB-INF/spring/context-root.xml
  	</param-value>
  </context-param>
  
  <listener>
  	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  
  <!-- spring security filter -->
  <filter>
  	<filter-name>springSecurityFilterChain</filter-name>
  	<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
  </filter>
  
  <filter-mapping>
  	<filter-name>springSecurityFilterChain</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping>
```


### 3. context-security.xml 을 생성해 준다
- Namespaces에서 beans 와 security를 체크해서 설정해준다
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:security="http://www.springframework.org/schema/security"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/security http://www.springframework.org/schema/security/spring-security-5.2.xsd">

	<!-- 인식하게 하기위해서 추가 -->
	<context:component-scan base-package="bit.com.a" />
	
<!-- 접근시에 로그인이 안 되어 있을 경우 , login 페이지로 강제 이동 -->
	<!-- <security:http auto-config="true" use-expressions="false">	
		<security:intercept-url pattern="/**" access="ROLE_USER"/> 어떠한 경로든 인터셉터(낚아채라) 
	</security:http> -->
	<security:http auto-config="true" use-expressions="true">	<!-- 아래 순서가 매우 중요하다 --> <!-- use-expressions = true 는 SpEL문법을 사용한다는 의미 -->
		 
		<security:intercept-url pattern="/user/**" access="hasAnyRole('ROLE_USER','ROLE_MEMBER','ROLE_ADMIN')"/>	<!-- hasAnyRole(SpEL문법) 은 안에 것 중에 하나 선택 -->
		<security:intercept-url pattern="/member/**" access="hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')"/>
		<security:intercept-url pattern="/admin/**" access="hasRole('ROLE_ADMIN')"/> <!-- 하나만 일때는 hasRole -->
		<security:intercept-url pattern="/**" access="permitAll"/> <!-- 게스트 모드는 모두 접근 가능 -->
		
		<!-- 사용자 지정 로그인 창을 아래의 주소와 내용으로 한다는 의미 -->
    <!-- default-target-url : 로그인 성공시 url -->
		<security:form-login username-parameter="loginId" password-parameter="loginPwd" login-page="/secu/loginPage" default-target-url="/"/>
		
		<!-- 접근 권한에 따른 메세지를 내보내는 링크 -->
		<security:access-denied-handler error-page="/access_deined_page"/>
	</security:http>
	
	
<!-- 우리가 필요한 부분을 셋팅 , 로그인 정보의 차등을 셋팅하는 부분 -->
	<security:authentication-manager>
		<security:authentication-provider>
			<security:user-service>
			<!-- 아래  코드는 현재 db를 접근하지 않기 때문에 임시 적으로 만들어 준것이다 -->
			<!-- 	<security:user name="guest" password="{noop}guest" authorities="ROLE_GUEST"/>
				<security:user name="user" password="{noop}userPw" authorities="ROLE_USER"/>
				 -->
				 <security:user name="user" password="{noop}userPw" authorities="ROLE_USER"/> <!-- {noop}은 스프링 4버전 이후로 암호화를 해주어야 하기때문에 암호화를 해주는 의미 -->
				 <security:user name="member" password="{noop}memPw" authorities="ROLE_MEMBER"/>
				 <security:user name="admin" password="{noop}admin" authorities="ROLE_ADMIN"/>
				 
				<!-- DB로 접근 -->
				<!-- 여기 자리 -->
				
			</security:user-service>
		</security:authentication-provider>
	</security:authentication-manager>	

</beans>
```
#### *참고 SpEL문법
```
#SpEL문법

hasRole("admin") - admin권한을 가지고 있어야 접근가능
hasAnyRole("admin", "user") - admin, user권한중 한가지만 가지고 있으면 접근가능

permitAll - 모두 접근가능

denyAll - 모두 접근불가능

isAnonymous() - 인증을 하지 않은 사용자일 경우(로그인하지 않은 사용자)

isRememberMe() - Remember-me 기능으로 로그인한 사용자일 경우(자동로그인 사용자)

isAuthenticated() - 인증을 한 사용자일 경우(로그인한 사용자)

isFullyAuthenticated() - 인증을 한 사용자이면서 Remember-me 기능으로 로그인하지 않은 사용자
```
### 4. 아까 만들어둔 로그인 페이지를 접근하는 컨트롤러와 접근 금지 페이지 (뷰)생성
```java
package bit.com.a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping(value = "/secu/loginPage")
	public String page() throws Exception{
		return "/secu/loginPage";
	}
	
	@RequestMapping(value = "access_deined_page")
	public String accessDeinedPage() throws Exception{
		return"/access_deined_page";
	}
}
```
### 5. view 
 - 로그인 접근 할 수있는 페이지 , 접근 권한을 확인해볼 페이지
 ```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <!--시큐리티를 사용하기 위해 추가 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/include/header.jspf" %>
</head>
<body>

<br><br>
	<div class="container text-center">
		<h1>Security Project</h1>
		<sec:authorize access="isAnonymous()">	<!-- 내장함수 접근 (누구든지 접근 가능)-->
		<h5>
			<a href='<c:url value="/secu/loginPage"/>' class="badge badge-pill badge-info">LOGIN</a>로그인해 주세요
		</h5>
		</sec:authorize>
	</div>
	<br><br>
  <!-- 아래는 접근 권한을 확인 하기위 한 페이지 -->
	<div class="container text-center col-2">
		<a href='<c:url value="/page"/>' role="button" class="btn btn-outline-secondary btn-block">GUEST</a>
		<a href='<c:url value="/user/page"/>' role="button" class="btn btn-outline-secondary btn-block">USER</a>
		<a href='<c:url value="/member/page"/>' role="button" class="btn btn-outline-secondary btn-block">MEMBER</a>
		<a href='<c:url value="/admin/page"/>' role="button" class="btn btn-outline-secondary btn-block">ADMIN</a>
	</div>

</body>
</html>
```  
- 로그인 페이지 (스프링 제공)
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/include/header.jspf"  %> <!-- css를 위한 포함이다 -->
<title>Login</title>
</head>
<body>
      <br><br>
      <div class="container text-center">
          <h1>Security Prj</h1><br>
      </div>
      <div class="container col-md-4">
	      <form class="px-4 py-3" action="/springSecurity/login" method="post"><!-- action은 프로젝트 명이다 -->
	          <div class="form-group">
	              <label for="exampleDropdownFormEmail1">ID</label>
	              <input type="text" class="form-control" name="loginId" placeholder="example">
	          </div>
	          <div class="form-group">
	              <label for="exampleDropdownFormPassword1">Password</label>
	              <input type="password" class="form-control" name="loginPwd" placeholder="Password">
	          </div>
	          <div class="form-check">
	              <label class="form-check-label">
	              <input type="checkbox" class="form-check-input">
	              Remember me
	              </label>
	          </div>
	          <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
	          <button type="submit" class="btn btn-primary">Sign in</button>
	      </form>
	      <div class="dropdown-divider"></div>
	      <a class="dropdown-item" href="#">New around here? Sign up</a>
	      <a class="dropdown-item" href="#">Forgot password?</a>
	  </div>

</body>
</html>
```
- 접근 권한 페이지(로그인시 부여되는 권한으로 접근이 되는 페이지가 있고 거부되는 페이지가 있다 )
```html
<%@include file="/WEB-INF/include/header.jspf" %>
</head>
<body>

<br><br>
<div class="container text-center">
	<h1>Guest Page</h1> <!-- 여기와 아래 권한을 알려주는곳만 각각 다르다 -->

</div>
<br><br>
<div class="container text-center">
	<h6 class="font-italic text-danger">Guest 페이지 입니다.<br>감사합니다</h6>

</div>
<br><br>

<div class="container text-center">
	<a href='<c:url value="/"/>' class="text-dark"><i class="fas fa-undo"></i></a>
</div>

</body>
</html>
```
- 접근 거부 페이지
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/include/header.jspf" %>
</head>
<body>
<br><br>
<div class="container text-center">
	<h1>Access Deined</h1>

</div>
<br><br>
<div class="container text-center">
	<h6 class="font-italic text-danger">접근 권한이 없습니다<br>관리자에게 문의하세요</h6>

</div>
<br><br>

<div class="container text-center">
	<a href='<c:url value="/"/>' class="text-dark"><i class="fas fa-undo"></i></a>
</div>
</body>
</html>
```
### 6. 각 접근권한이 걸린 페이지의 컨트롤러
```java
package bit.com.a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class pageController {

	@RequestMapping(value = "/page")
	public String page()throws Exception{
		return "/page";
	}
	
	@RequestMapping(value = "/user/page")
	public String userPage()throws Exception{
		return "/user/page";
	}
	
	@RequestMapping(value = "/member/page")
	public String memberPage()throws Exception{
		return "/member/page";
	}
	
	@RequestMapping(value = "/admin/page")
	public String adminPage()throws Exception{
		return "/admin/page";
	}
}
```

# DB 연동하여 로그인 하기
### 디비에서 회원정보를 담을 테이블을 생성한다 (이전 member 테이블과는 구성이 다르다)
```
CREATE TABLE USERTABLE(
	ID VARCHAR2(100) PRIMARY KEY,
	PASSWORD VARCHAR2(300) NOT NULL,
	NAME VARCHAR2(45) NOT NULL,
	AUTHORITY VARCHAR2(50) NOT NULL,
	ENABLED NUMBER(1)
)
```
### model(dto)를 생성 해준다 (시큐리티에서 설정한 요소들이 들어가 있어야한다)
- UserDetails, Serializable 두개의 인터페이스를 상속받고 오버라이드 해준다
```java
package bit.com.a.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails, Serializable {

	private String ID;
	private String PASSWORD;
	private String NAME;
	
	// 아래가 시큐리트에 필요한 내용 위에 변수(컬럼)은 더 추가 할 수 있다
	private String AUTHORITY;	//권한	
	private boolean ENABLED; //접근 가능 여부
	
	public CustomUserDetails() {
	}

	@Override
	//컬렉션으로 잡은 이유는 권한설정을 다중으로 할 수 있기 때문에 / GrantedAuthority: 허가, 권리
	public Collection<? extends GrantedAuthority> getAuthorities() {	//권한들을 리턴하는 함수
		
		//여러개의 권한들을 리스트로 받아서 넣어주고 리턴해 준다
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(AUTHORITY));
		
		return auth;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return PASSWORD;	//리턴값에 설정한 변수들을 넣는다
	}

	@Override
	public String getUsername() {	//id == name 으로 사용한다
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public boolean isAccountNonExpired() {	//계정이 만료된 계정인지
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {	// 계정이 잠겨 있는지
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {	//계정의 패스워드가 만료되지 않았는지
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {	//사용 가능 여부
		// TODO Auto-generated method stub
		return true;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}
	
}
```
### DB에 접근하여 회원정보를 꺼내올 쿼리문을 작성해준다
- parameterType없이 반환되는 값만 있다
```xml
<mapper namespace="CustUser">
<select id="selectUserById" resultType="bit.com.a.model.CustomUserDetails">
	SELECT *
	FROM USERTABLE
	WHERE ID = #{loginId}

</select>
```
### dao를 생성해 준다
```java
package bit.com.a.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.model.CustomUserDetails;

@Repository
public class CustomUserDao {

	@Autowired
	private SqlSessionTemplate session;
	
	public CustomUserDetails getUserById(String username) {
		return session.selectOne("CustUser.selectUserById", username);
				
	}
}
```
### service를 생성해주는데, UserDetailsService 을 상속받고 오버라이드 해준다(loadUserByUsername 생성)
- dao에서 받은 정보들은 model에 담고 반환하는 코드를 작성해준다

```java
package bit.com.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import bit.com.a.dao.CustomUserDao;
import bit.com.a.model.CustomUserDetails;


public class CustomUserService implements UserDetailsService {

	@Autowired
	CustomUserDao dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("CustomUserService loadUserByUsername");
		
		CustomUserDetails user = dao.getUserById(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return user;
	}

}
```
### service에서 인증을 대조하는 클래스 생성( 가장 중요)
- AuthenticationProvider 상속받고 오버 라이드 해준다
- 뷰에서 입력받은 정보와 db에서 정보를 가져와 패스워드를 대조하고 최종 아이디, 패스워드 , 권한을 
```java
package bit.com.a.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import bit.com.a.HelloController;
import bit.com.a.model.CustomUserDetails;
				// Authentication : 확증 , Provider: 제공
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private static Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	@Autowired
	private UserDetailsService userDeser;	//상속받은 부분의 인터페이스를 가져오기 떄문에 CustomUserService를 가져온다
	
	public boolean matchPassword(String loginPwd, String password) {
		logger.info("matchPassword check");
		return loginPwd.equals(password);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 인증 함수
		
		//입력된 정보를 가져온것
		String username = (String)authentication.getPrincipal(); //id 가 넘어온다
		String password = (String)authentication.getCredentials();//password가 넘어온다
		
		logger.info("CustomAuthenticationProvider authenticate");
		logger.info("password:"+password);
		
		//db로 부터 가져온 정보
		CustomUserDetails user = (CustomUserDetails)userDeser.loadUserByUsername(username);
		Collection<GrantedAuthority> authotities = (Collection<GrantedAuthority>)user.getAuthorities(); //권한을 가져온다
		
		if(!matchPassword(password, user.getPassword())) { //패스워드가 다른 경우
			System.out.println("패스워드 다름");
			throw new BadCredentialsException(username);
		}
		
		if(!user.isEnabled()) {	//유저 활성화가 안되어있는 경우
			throw new BadCredentialsException(username);
		}
		
		return new UsernamePasswordAuthenticationToken(username, password, authotities);
	}

	@Override //사실 이부부은 나중에 반환된 위의 authenticate 메소드에서 반환한 객체가 유효한 타입이 맞는지 검사
			// null 값이거나 잘못된 타입을 반환했을 경우 인증 실패로 간주
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;	//true 전환 (강제로 true)
	}
}
```

### context-security.xml 에서 이전에 임의로 회원을 추가했던 부분을 사용하지 않고 디비에서 불러온 부분을 사용
```xml
<!-- db 적용 -->
<bean id="userService" class="bit.com.a.service.CustomUserService"/>	<!-- 이렇게 생성해주기때문에 autowired 안해줘도 된다 -->

<bean id="userAuthProvider" class="bit.com.a.service.CustomAuthenticationProvider"/>	<!-- 인증확인 부분의 클래스를 생성-->

<security:authentication-manager>
	<security:authentication-provider ref="userAuthProvider"/>	<!-- 참조부분에 위에서 생성한 userAuthProvider를 넣어준다 -->
</security:authentication-manager>
```

